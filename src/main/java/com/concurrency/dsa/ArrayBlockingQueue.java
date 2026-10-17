package com.concurrency.dsa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> {
    private final Lock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final T[] items;
    private int head, tail, count;

    public ArrayBlockingQueue(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    public void enq(T x) throws InterruptedException {
        lock.lock();
        try{
            while(count==items.length){
                notFull.await();
            }

            //no longer full
            items[tail++] = x;
            if(tail==items.length){
                tail=0;
            }

            ++count;

            //no longer empty
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T deq() throws InterruptedException {
        lock.lock();
        try{
            while(count==0){
                notEmpty.await();
            }

            //no longer empty
            T e = items[head];
            items[head] = null; //GC
            if(++head==items.length){
                head=0;
            }

            --count;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
