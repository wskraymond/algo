package com.concurrency.dsa;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ArraySpinLock {
    private final AtomicInteger tail = new AtomicInteger();
    private final AtomicIntegerArray flags;

    private final int capacity;
    private static final int NUM_PADS = 4;
    private final int mask;

    private final ThreadLocal<Integer> mySlot = ThreadLocal.withInitial( ()-> -1);

    public ArraySpinLock(int capacity){
        if(Integer.bitCount(capacity)!=1){
            throw new IllegalArgumentException("capacity must be 2^n");
        }

        this.capacity = capacity* NUM_PADS;

        this.flags = new AtomicIntegerArray(this.capacity);
        this.flags.set(0,1);
        this.mask = this.capacity -1;
    }

    private int index(int slot){
        return slot * NUM_PADS & this.mask;
    }

    public void lock(){
        int slot = tail.getAndIncrement();
        mySlot.set(slot);
        int index = index(slot);
        while(flags.get(index)==0){
            Thread.onSpinWait();
        }
    }

    public void unlock(){
        int slot = mySlot.get();
        flags.set(index(slot), 0);
        flags.set(index(slot+1), 1);
        mySlot.remove();
    }
}
