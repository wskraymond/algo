package com.practice.conc;

import java.util.List;

/*
    ConcurrentModificationException can also arise in single‐threaded code as well;
    this happens when objects are removed from the collection directly rather than through Iterator.remove.


    This iteration idiom relies on a leap of faith that other threads will not modify the Vector between the calls to size and
    get. In a single‐threaded environment, this assumption is perfectly valid, but when other threads may concurrently
    modify the Vector it can lead to trouble. Just as with getLast, if another thread deletes an element while you are
    iterating through the Vector and the operations are interleaved unluckily, this iteration idiom throws
    ArrayIndexOutOfBoundsException.
 */
public class SafeIteration<E> {
    private final List<E> list;

    public SafeIteration(List<E> list) {
        this.list = list;
    }

    public Object getLast() {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public void deleteLast() {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

    /**
     *  (The collection still must be locked during the clone operation itself.) Cloning the
     * collection has an obvious performance cost; whether this is a favorable tradeoff depends on many factors including the
     * size of the collection, how much work is done for each element,
     * @return
     */
    private E[] toArray() {
        synchronized (list) {
            return (E[]) list.toArray();
        }
    }

    /**
     * An alternative to locking the collection during iteration is to clone the collection and iterate the copy instead. Since the
     * clone is thread‐confined, no other thread can modify it during iteration, eliminating the possibility of
     * ConcurrentModificationException
     */
    public void lockFreelyIterate() {
        for (E e : toArray())
            doSomething(e);
    }

    /**
     * locking collections for significant periods of time hurts application scalability. The longer a lock is held, the
     * more likely it is to be contended, and if many threads are blocked waiting for a lock throughput and CPU utilization can
     * suffer
     */
    public void safelyIterate() {
        synchronized (list) {
            for (int i = 0; i < list.size(); i++)
                doSomething(list.get(i));
        }
    }

    /**
     * These fail‐fast iterators are not designed to be foolproof ‐ they are designed to catch concurrency errors on a "good‐
     * faith‐effort" basis and thus act only as early‐warning indicators for concurrency problems.
     *
     * They are implemented by associating a modification count with the collection: if the modification count changes during iteration, hasNext or next
     * throws ConcurrentModificationException.
     *
     * However, this check is done without synchronization, so there is a risk of
         * seeing a stale value of the modification count and therefore that the iterator does not realize a modification has been
         * made. This was a deliberate design tradeoff to reduce the performance impact of the concurrent modification detection
         * code
     *
     */
    public void unsafelyIterate() {
        /*
        throw an exception, this doesn't mean list isn't thread‐safe. The state of
        the list is still valid and the exception is in fact in conformance with its specification.
         */
        for (int i = 0; i < list.size(); i++) {
            this.doSomething(list.get(i)); // may throw ConcurrentModificationException and may throw ArrayIndexOutOfBoundsException
        }
    }

    private void doSomething(E e) {
        // do something
    }

}
