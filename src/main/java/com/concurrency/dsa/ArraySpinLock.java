package com.concurrency.dsa;

import jdk.internal.vm.annotation.Contended;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ArraySpinLock {
    private final AtomicIntegerArray flags;

    /**
     * Mac OS: m4 chip
     * run: sysctl  hw.cachelinesize
     * output: hw.cachelinesize: 128 bytes
     * int ->  4 bytes
     * 128/4 = 32 int
     */
    private static final int NUM_PADS = 32;
    private final int mask;

//    private final ThreadLocal<Integer> mySlot = ThreadLocal.withInitial( ()-> null); //overkill
//    private final AtomicInteger tail = new AtomicInteger();

    private static final VarHandle TAIL;
    static {
        try {
            TAIL = MethodHandles.lookup()
                    .findVarHandle(ArraySpinLock.class, "tail", int.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // every arriving thread hits this with getAndIncrement
    @Contended("tail")
    private volatile int tail;


    /* re-entrance support */
    //------------holder cacheline---------------------
    @Contended("holder")
    private volatile Thread owner; //shared
    //----owner variable----
    @Contended("holder")
    private int mySlot;
    @Contended("holder")
    private int holds;
    //----------------------
    //----------------------------------------------------

    @Contended("end")
    private int endPad;

    public ArraySpinLock(int capacity){
        if(Integer.bitCount(capacity)!=1){
            throw new IllegalArgumentException("capacity must be 2^n");
        }

        this.flags = new AtomicIntegerArray((capacity+1)* NUM_PADS); //+1 for front pad
        this.mask = capacity*NUM_PADS - 1;
        this.flags.set(index(0),1);
    }

    private int getTailAndIncrement(){
        return (int) TAIL.getAndAdd(this, 1);
    }

    private int index(int slot){
        return (slot * NUM_PADS & this.mask) + NUM_PADS;
    }

    private void acquired(Thread me, int slot){
        this.owner = me;
        this.mySlot = slot;
        this.holds = 1;
    }

    public void lock(){
        Thread me = Thread.currentThread();
        if(owner==me){
            holds++;
            return;
        }

        int slot = getTailAndIncrement();
        int index = index(slot);
        while(flags.get(index)==0){
            Thread.onSpinWait();
        }

        acquired(me, slot);
    }

    private int released(){
        this.owner=null;
        return this.mySlot;
    }

    public void unlock(){
        if(owner!=Thread.currentThread()){
            throw new IllegalMonitorStateException();
        }

        if(--holds>0){
            return;
        }

        int slot = released();
        flags.set(index(slot), 0);
        flags.set(index(slot + 1), 1);
    }
}
