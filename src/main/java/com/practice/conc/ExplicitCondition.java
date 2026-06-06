package com.practice.conc;

import com.mine.anno.GuardedBy;
import com.mine.anno.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitCondition {
    /**
     * just as Lock offers a richer feature set than intrinsic
     * locking, Condition offers a richer feature set than intrinsic condition queues: multiple wait sets per lock, interruptible
     * and uninterruptible condition waits, deadline‐based waiting, and a choice of fair or non-fair queueing.
     *
     * 1. Multiple condition queues per lock, inherit fairness setting of their associated Lock
     * Unlike intrinsic condition queues, you can have as many Condition objects per Lock as you want.
     * Condition objects inherit the fairness setting of their associated Lock
     * (for fair locks, threads are released from Condition.await in FIF Order)
     *
     * 2. Conditional and signle notification, more efficient than notifyAll
     *      - By separating the two condition predicates into separate wait sets,
     *        Condition makes it easier to meet the requirements for single notification.
     *      - Using the more efficient signal instead of signalAll
     *        reduces the number of context switches and lock acquisitions triggered by each buffer operation.
     *
     * 3. 3-way relationship between locking, waiting, and condition predicates
     * The lock acquisition criteria must also hold when using explicit Locks and Conditions.
     *   - The variables involved in the condition predicate must be guarded by the Lock,
     *   - and the Lock must be held when testing the condition predicate
     *   - and when calling await and signal.
     *
     * 4. choose between intrinsic and explicit condition queues
     *   1. use Condition if you need its advanced features such as fair queueing or
     *      multiple wait sets per lock
     *   2. otherwise prefer intrinsic condition queues.
     *
     */
}

@ThreadSafe
class ConditionBoundedBuffer<T> {
    private static final int BUFFER_SIZE = 100;

    protected final Lock lock = new ReentrantLock();
    // CONDITION PREDICATE: notFull (count < items.length)
    private final Condition notFull = lock.newCondition();
    // CONDITION PREDICATE: notEmpty (count > 0)
    private final Condition notEmpty = lock.newCondition();

    @GuardedBy("lock")
    private final T[] items = (T[]) new Object[BUFFER_SIZE];

    @GuardedBy("lock")
    private int tail, head, count;

    // BLOCKS-UNTIL: notFull
    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = x;
            if (++tail == items.length)
                tail = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    // BLOCKS-UNTIL: notEmpty
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T x = items[head];
            items[head] = null;
            if (++head == items.length)
                head = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

// Not really how java.util.concurrent.Semaphore is implemented
//but built using Abstract-QueuedSynchronizer (AQS) (in java 5)
@ThreadSafe
class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();

    // CONDITION PREDICATE: permitsAvailable (permits > 0)
    private final Condition permitsAvailable = lock.newCondition();

    @GuardedBy("lock")
    private int permits;

    SemaphoreOnLock(int initialPermits) {
        lock.lock();
        try {
            permits = initialPermits;
        } finally {
            lock.unlock();
        }
    }

    // BLOCKS-UNTIL: permitsAvailable
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }

            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
