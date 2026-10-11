package com.concurrency.dsa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The same assertions run against ArraySpinLock and ReentrantLock(true).
 * If the reference lock passes and ArraySpinLock fails, the bug is in ArraySpinLock.
 */
@RunWith(Parameterized.class)
public class LockCorrectnessTest {

    interface SimpleLock {
        void lock();
        void unlock();
    }

    static final int THREADS = 4; // must stay <= ArraySpinLock capacity

    static SimpleLock arraySpin() {
        ArraySpinLock l = new ArraySpinLock(8);
        return new SimpleLock() {
            public void lock() { l.lock(); }
            public void unlock() { l.unlock(); }
        };
    }

    static SimpleLock reentrantFair() {
        ReentrantLock l = new ReentrantLock(true);
        return new SimpleLock() {
            public void lock() { l.lock(); }
            public void unlock() { l.unlock(); }
        };
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> impls() {
        return Arrays.asList(new Object[][]{
                {"ArraySpinLock", (Supplier<SimpleLock>) LockCorrectnessTest::arraySpin},
                {"ReentrantLock(true)", (Supplier<SimpleLock>) LockCorrectnessTest::reentrantFair},
        });
    }

    private final Supplier<SimpleLock> factory;

    public LockCorrectnessTest(String name, Supplier<SimpleLock> factory) {
        this.factory = factory;
    }

    private static Thread daemon(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true); // a deadlocked lock must not keep the JVM alive
        return t;
    }

    static void runThreads(int n, Runnable body) throws Exception {
        Thread[] ts = new Thread[n];
        CyclicBarrier barrier = new CyclicBarrier(n);
        AtomicReference<Throwable> err = new AtomicReference<>();
        for (int i = 0; i < n; i++) {
            ts[i] = daemon(() -> {
                try {
                    barrier.await();
                    body.run();
                } catch (Throwable t) {
                    err.compareAndSet(null, t);
                }
            });
            ts[i].start();
        }
        for (Thread t : ts) {
            t.join();
        }
        if (err.get() != null) {
            throw new AssertionError("worker failed", err.get());
        }
    }

    @Test(timeout = 60_000)
    public void noLostUpdates() throws Exception {
        SimpleLock lock = factory.get();
        int iters = 200_000;
        int[] counter = {0}; // plain on purpose: only the lock protects it
        runThreads(THREADS, () -> {
            for (int i = 0; i < iters; i++) {
                lock.lock();
                try {
                    counter[0]++;
                } finally {
                    lock.unlock();
                }
            }
        });
        assertEquals(THREADS * iters, counter[0]);
    }

    @Test(timeout = 60_000)
    public void neverTwoThreadsInsideCriticalSection() throws Exception {
        SimpleLock lock = factory.get();
        AtomicInteger inside = new AtomicInteger();
        AtomicBoolean violated = new AtomicBoolean();
        runThreads(THREADS, () -> {
            for (int i = 0; i < 100_000; i++) {
                lock.lock();
                try {
                    if (inside.incrementAndGet() != 1) {
                        violated.set(true);
                    }
                    inside.decrementAndGet();
                } finally {
                    lock.unlock();
                }
            }
        });
        assertFalse("two threads were inside the critical section at once", violated.get());
    }

    @Test(timeout = 60_000)
    public void plainWritesArePublishedToNextOwner() throws Exception {
        SimpleLock lock = factory.get();
        int[] a = {0};
        int[] b = {0};
        AtomicBoolean torn = new AtomicBoolean();
        runThreads(THREADS, () -> {
            for (int i = 0; i < 100_000; i++) {
                lock.lock();
                try {
                    if (a[0] != b[0]) {
                        torn.set(true);
                    }
                    a[0]++;
                    b[0]++;
                } finally {
                    lock.unlock();
                }
            }
        });
        assertFalse("next owner saw a half-finished critical section", torn.get());
        assertEquals(THREADS * 100_000, a[0]);
    }

    @Test(timeout = 60_000)
    public void singleThreadCanReuseLockManyTimes() {
        SimpleLock lock = factory.get();
        int count = 0;
        for (int i = 0; i < 1_000_000; i++) { // far beyond ring capacity, slots wrap around
            lock.lock();
            count++;
            lock.unlock();
        }
        assertEquals(1_000_000, count);
    }

    @Test(timeout = 60_000)
    public void nestedLockingCountsHolds() throws Exception {
        SimpleLock lock = factory.get();
        for (int i = 0; i < 10; i++) {
            lock.lock();
        }
        AtomicBoolean acquired = new AtomicBoolean();
        Thread other = daemon(() -> {
            lock.lock();
            acquired.set(true);
            lock.unlock();
        });
        other.start();
        for (int i = 0; i < 9; i++) { // release 9 of 10 holds
            lock.unlock();
        }
        Thread.sleep(200);
        assertFalse("other thread got in while 1 hold remained", acquired.get());
        lock.unlock();
        other.join(5_000);
        assertTrue("other thread never got the lock after the last unlock", acquired.get());
    }

    @Test(timeout = 60_000)
    public void unlockByNonOwnerThrowsAndDoesNotRelease() throws Exception {
        SimpleLock lock = factory.get();
        lock.lock();

        AtomicBoolean threw = new AtomicBoolean();
        Thread intruder = daemon(() -> {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException expected) {
                threw.set(true);
            }
        });
        intruder.start();
        intruder.join(5_000);
        assertTrue("non-owner unlock must throw IllegalMonitorStateException", threw.get());

        AtomicBoolean acquired = new AtomicBoolean();
        Thread waiter = daemon(() -> {
            lock.lock();
            acquired.set(true);
            lock.unlock();
        });
        waiter.start();
        Thread.sleep(200);
        assertFalse("non-owner unlock released the lock", acquired.get());
        lock.unlock();
        waiter.join(5_000);
        assertTrue(acquired.get());
    }

    @Test(timeout = 60_000)
    public void unlockWithoutLockThrows() {
        SimpleLock lock = factory.get();
        try {
            lock.unlock();
            throw new AssertionError("expected IllegalMonitorStateException");
        } catch (IllegalMonitorStateException expected) {
            // ok
        }
    }

    @Test(timeout = 60_000)
    public void handsOffInArrivalOrder() throws Exception {
        SimpleLock lock = factory.get();
        List<Integer> order = Collections.synchronizedList(new ArrayList<>());
        lock.lock();
        Thread[] ts = new Thread[3];
        for (int i = 0; i < ts.length; i++) {
            final int id = i;
            ts[i] = daemon(() -> {
                lock.lock();
                order.add(id);
                lock.unlock();
            });
            ts[i].start();
            Thread.sleep(200); // timing based: each thread queues before the next one starts
        }
        lock.unlock();
        for (Thread t : ts) {
            t.join(5_000);
        }
        assertEquals(Arrays.asList(0, 1, 2), order);
    }
}
