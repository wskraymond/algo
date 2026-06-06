package com.practice.conc.lockfree;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicUpdator_under_serialization_and_freq_alloc {
    /**
     * 1. Frequently allocated, short‐lived objects
     * For frequently allocated, short‐lived objects like queue link nodes,
     * eliminating the creation of an AtomicReference for each Node is significant
     *
     * 2. Serialization needed
     * when you want to perform atomic updates while preserving the serialized form of an existing class.
     *
     * Solution: Atomic field updaters
     * The atomic field updater classes (available in Integer, Long, and Reference versions) represent
     *      - a java reflection‐based "view" of an existing volatile field
     *          - so that CAS can be used on existing volatile fields.
     *          - No constructors; call the newUpdater factory method
     *                      - specifying the class and field name.
     */
}

class MyConcurrentLinkedQueue<E> {
    private static class Node<E> {
        final E item;
        /**
         * Instead of representing each Node with an atomic reference,
         * uses an ordinary volatile reference
         * and updates it through the reflection‐based AtomicReferenceFieldUpdater
         */
        volatile Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    private final AtomicReferenceFieldUpdater<Node, Node> nextUpdater = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");

    private final Node<E> dummy = new Node<E>(null);  //sentinel node
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

        public boolean put(E item) {
            Node<E> newNode = new Node<E>(item);
            while (true) {
                Node<E> curTail = tail.get();
                Node<E> tailNext = curTail.next;
                if (curTail == tail.get()) {
                    if (tailNext != null) {     // Queue in intermediate state, advance tail
                        tail.compareAndSet(curTail, tailNext);
                    } else {    // In quiescent state, try inserting new node
                        /**
                         * updates to the next field of a Node are applied
                         *      - using the compareAndSet method of nextUpdater.
                         */
                        if (nextUpdater.compareAndSet(curTail, null, newNode)) {    // Insertion succeeded, try advancing tail
                            tail.compareAndSet(curTail, newNode);
                            return true;
                        }
                    }
                }
            }
        }

}
