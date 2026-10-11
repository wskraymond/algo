package com.concurrency.dsa;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAUnboundedStack<T> {
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
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null; // we don't need AtomicReference here
        }
    }

    private final AtomicStampedReference<Node<T>> top = new AtomicStampedReference<>(null, 0);

    private Node<T> alloc(T value){
        return new Node<>(value);
    }

    private void free(Node<T> node){
        node.value = null;
        node.next = null;
    }

    protected boolean tryPush(Node<T> newTop){
        int[] stamp = new int[1];
        Node<T> oldTop = top.get(stamp);
        if(top.getStamp()==stamp[0]){
            newTop.next = oldTop;
            return top.compareAndSet(oldTop, newTop, stamp[0], stamp[0]+1);
        }

        return false;
    }

    public void push(T value){
        Node<T> node = alloc(value);
        while(true){
            if(tryPush(node)){
                return;
            } else {
                //backoff
                Thread.yield();
            }
        }
    }

    protected Node<T> tryPop() throws EmptyException{
        int[] stamp = new int[1];
        Node<T> oldTop = top.get(stamp);
        if(top.getStamp()==stamp[0]){
            if(oldTop==null){
                throw new EmptyException();
            }

            Node<T> newTop = oldTop.next;
            if(top.compareAndSet(oldTop, newTop, stamp[0], stamp[0]+1)){
                return oldTop;
            } else {
                return null;
            }
        }

        return null;
    }



    public T pop() throws EmptyException{
        while(true){
            Node<T> node = tryPop();
            if(node!=null){
                T e = node.value;
                free(node);
                return e;
            } else {
                //backoff
                Thread.yield();
            }
        }
    }
}
