package com.practice.conc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicStampedReference;

// Correct HFT Order Pool — ABA-proof with stamp versioning
/*
    A correct lock-free LIFO array stack in Java without DCAS (double CAS)  does not exist in a simple form.
    Treiber Stack below is the correct and canonical answer.
 */
public class SafeOrderPool {

    static class Order {
        volatile String symbol;
        volatile double price;
        volatile int quantity;
        Order next;
        final int id;

        Order(int id) { this.id = id; }

        void init(String symbol, double price, int quantity) {
            this.symbol   = symbol;
            this.price    = price;
            this.quantity = quantity;
            this.next     = null;
        }

        @Override
        public String toString() {
            return String.format("Order#%d[%s %d@%.2f]", id, symbol, quantity, price);
        }
    }

    // Stamp acts as a monotonic version counter — even if the same Order node
    // returns to the head, the stamp is different, so stale CAS is rejected.
    private final AtomicStampedReference<Order> freeList;

    public SafeOrderPool(int capacity) {
        Order head = null;
        for (int i = capacity - 1; i >= 0; i--) {
            Order o = new Order(i);
            o.next = head;
            head   = o;
        }
        freeList = new AtomicStampedReference<>(head, 0);
    }

    public Order acquire() {
        int[] stamp = new int[1];
        Order head, next;
        do {
            head = freeList.get(stamp);          // read ref + stamp atomically
            if (head == null) throw new RuntimeException("Pool exhausted");
            next = head.next;
            // ✅ Even if Thread 2 pops and re-pushes the same Order node,
            //    the stamp will have incremented (e.g. 5 → 7), so this CAS
            //    (expecting stamp=5) correctly FAILS and retries.
        } while (!freeList.compareAndSet(head, next, stamp[0], stamp[0] + 1));
        return head;
    }

    public void release(Order order) {
        int[] stamp = new int[1];
        Order head;
        do {
            head       = freeList.get(stamp);
            order.next = head;
        } while (!freeList.compareAndSet(head, order, stamp[0], stamp[0] + 1));
    }

    /**
     * sometimes we really want to ask "Has the value of V changed since I last observed it to be A?" For
     * some algorithms, changing V from A to B and then back to A still counts as a change that requires us to retry some
     * algorithmic step.
     *
     * This ABA problem can arise in algorithms that do their own memory management for link node objects. In this case, that
     * the head of a list still refers to a previously observed node is not enough to imply that the contents of the list have not
     * changed.
     *
     * If you cannot avoid the ABA problem by letting the garbage collector manage link nodes for you,
     *      - relatively simple solution: AtomicStampedReference (and boolean: AtomicMarkableReference)
     *      - instead of updating the value of a reference, update a pair of values,
     *          - a reference and a version number.
     *          - Even if the value changes from A to B and back to A,
     *              the version numbers will be different
     *
     * AtomicStampedReference:
     *      - updates an object reference‐integer pair
     *          - allowing "versioned" references that are immune to the ABA problem.
     *
     * AtomicMarkableReference:
     *      - updates an object reference‐boolean pair that is used by some algorithms
     *          - to let a node remain in a list while being marked as deleted.
     */

    /**
     *
     *  * Why HFT Needs Object Pools
     *  * In HFT systems, GC stop-the-world pauses are catastrophic — even microseconds matter.
     *  The solution is to pre-allocate a fixed number of Order objects
     *  and recycle them rather than let GC collect them.
     *
     *  But recycling the same objects back into the free list is precisely what triggers ABA
     *
     *  */
    public void main(String[] args) {
        SafeOrderPool pool = new SafeOrderPool(1000); // pre-allocate 1000 orders — no GC ever
        Queue<Order> orderQueue = new ConcurrentLinkedQueue<>(); // HFT matching engine queue
// Producer thread — fires market orders
        Runnable producer = () -> {
            for (int i = 0; i < 500; i++) {
                SafeOrderPool.Order o = pool.acquire();
                o.init("AAPL", 182.50 + i * 0.01, 100);
                orderQueue.add(o);   // pass to matching engine
            }
        };

// Consumer thread — matching engine recycles orders after execution
        Runnable consumer = () -> {
            SafeOrderPool.Order o;
            while ((o = orderQueue.poll()) != null) {
                //matchAndExecute(o);
                pool.release(o);         // recycle: same object goes back to the pool ♻️
            }
        };

    }
}
