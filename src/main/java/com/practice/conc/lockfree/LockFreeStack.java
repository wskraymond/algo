package com.practice.conc.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
        /*
            uses CAS to try to install it on the top of the stack.
            If the same node is still on the top of the stack as when we started, the CAS succeeds;

            if the top node has changed (because another thread has added or removed elements since we started),
            the CAS fails and push updates the new node based on the current stack state and tries again.
         */
    }

    /**
     * When a thread examines the stack,
     * it does so by calling get on the same AtomicReference,
     * which has the memory effects of a volatile read.
     *
     * @return
     */
    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node <E> {
        public final E item;
        public Node<E> next;
        public Node(E item) {
            this.item = item;
        }
    }
}
