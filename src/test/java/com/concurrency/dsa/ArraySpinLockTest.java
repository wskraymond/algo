package com.concurrency.dsa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Behaviour that only exists because ArraySpinLock is a bounded ring. */
public class ArraySpinLockTest {

    private static Thread daemon(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsZeroCapacity() {
        new ArraySpinLock(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNonPowerOfTwo3() {
        new ArraySpinLock(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNonPowerOfTwo6() {
        new ArraySpinLock(6);
    }

    @Test
    public void acceptsPowersOfTwo() {
        new ArraySpinLock(1);
        new ArraySpinLock(2);
        new ArraySpinLock(64);
    }

    @Test(timeout = 30_000)
    public void smallRingWrapsAroundWithTwoThreads() throws Exception {
        ArraySpinLock l = new ArraySpinLock(2);
        int[] counter = {0};
        int iters = 200_000;
        Runnable body = () -> {
            for (int i = 0; i < iters; i++) {
                l.lock();
                counter[0]++;
                l.unlock();
            }
        };
        Thread a = daemon(body);
        Thread b = daemon(body);
        a.start();
        b.start();
        a.join();
        b.join();
        assertEquals(2 * iters, counter[0]);
    }

    @Test(timeout = 30_000)
    public void ringFullOfThreadsStillExcludes() throws Exception {
        int n = 4; // threads == capacity is the supported maximum
        ArraySpinLock l = new ArraySpinLock(n);
        int[] counter = {0};
        int iters = 100_000;
        Thread[] ts = new Thread[n];
        for (int i = 0; i < n; i++) {
            ts[i] = daemon(() -> {
                for (int k = 0; k < iters; k++) {
                    l.lock();
                    counter[0]++;
                    l.unlock();
                }
            });
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        assertEquals(n * iters, counter[0]);
    }
}
