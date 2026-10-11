package com.concurrency.dsa;

import java.util.concurrent.atomic.AtomicStampedReference;


public class ABAUnboundedQueue<T> {
    public static class EmptyException extends Exception{
        public EmptyException(String message, Throwable cause) {
            super(message, cause);
        }

        public EmptyException(String message) {
            super(message);
        }

        public EmptyException() {
        }
    }

    private static class Node<T>{
        T value;
        AtomicStampedReference<Node<T>> next;

        public Node(T value) {
            this.value = value;
            this.next = new AtomicStampedReference<>(null, 0);
        }
    }

    private final AtomicStampedReference<Node<T>> head, tail;

    public ABAUnboundedQueue() {
        Node node = new Node(null); //head and tail is using the same dummy node at the very beginning
        this.head = new AtomicStampedReference<>(node, 0);
        this.tail = new AtomicStampedReference<>(node, 0);
    }

    private void free(Node node){
        node.value = null; //we can use lock-free stack with exchanger
    }

    public T deq() throws EmptyException {
        int[] lastStamp = new int[1];
        int[] firstStamp = new int[1];
        int[] nextStamp = new int[1];
        while(true){
            Node<T> first = head.get(firstStamp);
            Node<T> last = tail.get(lastStamp);
            Node<T> next = first.next.get(nextStamp);

            //in case of the fact that an object had been reclaimed and then inserted again
            if(head.getStamp()==firstStamp[0]){
                //check empty
                if(first==last){
                    if(next==null){
                        throw new EmptyException();
                    }

                    //help forward
                    tail.compareAndSet(last, next, lastStamp[0], lastStamp[0]+1);
                } else {
                    //poll head element
                    T value = next.value;
                    if(head.compareAndSet(first, next, firstStamp[0], firstStamp[0]+1)){
                        free(first);
                        return value;
                    }
                }
            }
        }
    }

    private Node<T> alloc(T value){
        return new Node<>(value);
    }

    public void enq(T value){
        Node<T> node = alloc(value);
        int[] lastStamp = new int[1];
        int[] nextStamp = new int[1];
        while(true){
            Node<T> last = tail.get(lastStamp);
            Node<T> next = last.next.get(nextStamp);

            if(tail.getStamp()==lastStamp[0]){
                if(next==null){
                    //1. link up new node to the tail of the list
                    if(last.next.compareAndSet(next, node, nextStamp[0], nextStamp[0]+1)){
                        //2. attempt once to move backward the tail pointer to the new node
                        tail.compareAndSet(last, node, lastStamp[0], lastStamp[0]+1);
                        return; //enqueued
                    }
                } else {
                    //help backward
                    tail.compareAndSet(last, next, lastStamp[0], lastStamp[0]+1);
                }
            }
        }
    }


}
