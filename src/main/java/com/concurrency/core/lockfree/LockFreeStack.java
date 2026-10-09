package com.concurrency.core.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<E> {
    private AtomicReference<Node<E>> top = new AtomicReference<>(null);

    public void push(E item){
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do{
            oldHead = top.get();
            newHead.next = oldHead;
        }while(!top.compareAndSet(oldHead, newHead));
    }

    public E pop(){
        Node<E> oldHead, newHead;
        do{
            oldHead = top.get();
            if(oldHead==null){
                return null;
            }

            newHead = oldHead.next;
        }while(!top.compareAndSet(oldHead, newHead));

        return oldHead.item;
    }


    public static class Node<E>{
        private final E item;
        private Node<E> next;
        public Node(E item){
            this.item = item;
        }
    }
}
