package com.practice.conc.lockfree;

/**
 * Design Simplicity
 * • With striping, the lock index is computed as bucketIndex % numberOfLocks.
 * • This keeps the lock array small and predictable, while still reducing contention significantly.
 * • One lock per bucket would complicate resizing and rehashing, since the lock structure would need to grow dynamically with the bucket array.
 *
 */
public class LockFreeHashMap{

}

class LockStriping{
    /*
        Lock striping is a more sophisticated technique that uses multiple locks to guard different parts of a single data structure.
        For example, a hash table can be protected by an array of locks, each guarding a different portion of the table.
        This allows multiple threads to access the table concurrently as long as they are accessing different portions of the table.
     */

    // Synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    public LockStriping(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++)
            locks[i] = new Object();
    }

    /**
     * # Partition locking on a variable sized set of independent objects #
     * Lock splitting can sometimes be extended to partition locking on a variable sized set of independent objects, in which
     * case it is called lock striping.
     *
     * For example, the implementation of ConcurrentHashMap uses an array of 16 locks, each of
     * which guards 1/16 of the hash buckets; bucket N is guarded by lock N mod 16.
     *
     * Assuming the hash function provides reasonable spreading characteristics and keys are accessed uniformly,
     * this should reduce the demand for any given lock by approximately a factor of 16.
     *
     * it is this technique that enables ConcurrentHashMap to support up to 16 concurrent writers.
     *
     * (The number of locks could be increased to provide even better concurrency under heavy access on high‐processor‐count systems,
     * but the number of stripes should be increased beyond the default of 16
     * only when you have evidence that concurrent writers are generating enough contention to warrant raising the limit.)
     *
     * @param key
     * @return
     */
    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key))
                    return m.value;
        }
        return null;
    }

    /*
        There are N_LOCKS locks, each
        guarding a subset of the buckets. Most methods, like get, need acquire only a single bucket lock. Some methods may
        need to acquire all the locks but, as in the implementation for clear, may not need to acquire them all
        simultaneously.
     */
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    /**
     * Avoiding use of hot-field counters, instad , have size enumerate the stripes and add up the number of elements in
     * @return
     */
    public int size() {
        /**
         * The simplest approach is to count the number of entries every time it is called. A common optimization is to update a
         * separate counter as entries are added or removed; this slightly increases the cost of a put or remove operation to keep
         * the counter up‐to‐date, but reduces the cost of the size operation from O(n) to O(1)
         */

        /**
         * # A hot field because every mutative operation needs to access it #
         * Even if you use lock striping for the hash
         * chains, synchronizing access to the counter reintroduces the scalability problems of exclusive locking. What looked like a
         * performance optimization ‐ caching the results of the size operation ‐ has turned into a scalability liability.
         */

        /*
            # Stripes enumeration and counter for each stripe #
            Having size enumerate the stripes and add up the number of elements in
            each stripe, instead of maintaining a global count. To avoid enumerating every element, ConcurrentHashMap maintains
            a separate count field for each stripe, also guarded by the stripe lock.
         */

        /*
            # further optimization by caching the collection size in a volatile #
            further optimize for this by caching the collection size in a
            volatile whenever size is called and invalidating the cache (setting it to ‐1) whenever the collection is modified. If the cached value is
            non-negative on entry to size, it is accurate and can be returned; otherwise it is recomputed.
         */

        //AI generated
        int size = 0;
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                for (Node m = buckets[i]; m != null; m = m.next)
                    size++;
            }
        }

        return size;
    }

    class Node {
        final Object key;
        Object value;
        Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}

/**
 * # Bucket-level CAS + synchronized nodes #
 * - readers don’t block writers, and writers don’t block readers.
 *      (Reads are lock-free, and writes only contend with other writes on the same bucket)
 * - Contention only occurs among writers targeting the same bucket.
 * - This is the big improvement over the old segment-based design, where a lock could block both readers and writers inside a segment.
 */
class LockFree_HashMap_In_Java8{
    /*
        changes how readers and writers interact.
        How it works in Java 8+
        • Reads (get operations):
            ◦ They are generally lock-free.
            ◦ A get just traverses the bucket chain or tree using volatile reads.
            ◦ No blocking occurs unless the table is being resized, in which case a read may briefly help with the transfer but doesn’t block in the traditional sense.
        • Writes (put/remove operations):
            ◦ Writers use CAS (compare-and-swap) to insert or update nodes.
            ◦ If CAS fails due to contention, they may fall back to synchronized blocks at the bucket/node level.
            ◦ This means writers can block other writers that target the same bucket, but they do not block readers.
        • Resizing:
            ◦ During table expansion, threads cooperate to move buckets.
            ◦ Readers may momentarily see intermediate states, but they don’t block — they retry or follow forwarding nodes until they reach the right bucket.
     */
}
