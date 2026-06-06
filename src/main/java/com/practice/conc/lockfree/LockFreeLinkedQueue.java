package com.practice.conc.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeLinkedQueue<E> {

    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    /*
        - an empty queue consists of a "sentinel" or "dummy" node,
        - and the head and tail pointers are initialized to refer to the sentinel.
        - The tail pointer always refers to the sentinel (if the queue is empty),
            - else, the last element in the queue,
            - or (in the case that an operation is in mid‐update) the second‐to‐last element.
     */
    private final Node<E> dummy = new Node<E>(null, null);  //sentinel node

    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);

    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {     // Queue in intermediate state, advance tail
                    tail.compareAndSet(curTail, tailNext);
                    /**
                     * repeats this check in case another thread has started inserting a new element,
                     *      - advancing the tail pointer
                     *          - until it finds the queue in the quiescent state
                     *      - so it can begin its own insertion.
                     */
                } else {    // In quiescent state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {    // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        /**
                         * if it fails, the inserting thread returns anyway
                         *      - rather than retrying the CAS,
                         *          - because no retry is needed
                         *              ‐ another thread has already finished the job
                         */
                        return true;
                    }
                }
            }
        }
    }
}