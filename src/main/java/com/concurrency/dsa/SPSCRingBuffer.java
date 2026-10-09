package com.concurrency.dsa;
import jdk.internal.vm.annotation.Contended;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class SPSCRingBuffer<T> {
    @Contended
    private static class PaddedCursor {
        long index;
        long cachedOther;
    }

    private final Object[] buff;
    private final int capacity;
    private final int mask;


    private final PaddedCursor producer;
    private final PaddedCursor consumer;

    private static final VarHandle indexBarrier;

    static {
        try{
            indexBarrier = MethodHandles.lookup().findVarHandle(PaddedCursor.class, "index", long.class);
        } catch (ReflectiveOperationException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public SPSCRingBuffer(int capacity) {
        if(capacity<2 || (capacity& (capacity-1))!=0){
            throw new IllegalArgumentException("capacity != 2^n");
        }

        this.buff = new Object[capacity];
        this.capacity = capacity;
        this.mask = capacity - 1;
        this.producer = new PaddedCursor();
        this.consumer = new PaddedCursor();
    }

    public boolean offer(T e){
        if(e==null) {
            throw new IllegalArgumentException("entry cannot be null");
        }

        final long writeIndex = producer.index;
        if(writeIndex - producer.cachedOther == this.capacity){
            producer.cachedOther = (long) indexBarrier.getAcquire(consumer);
            if(writeIndex - producer.cachedOther == this.capacity){
                return false;
            }
        }

        buff[(int) (writeIndex & mask)] = e;
        indexBarrier.setRelease(producer, writeIndex+1);
        return true;
    }

    public T poll(){
        final long readIndex = consumer.index;
        if(readIndex == consumer.cachedOther){
            consumer.cachedOther = (long) indexBarrier.getAcquire(producer);
            if(readIndex == consumer.cachedOther){
                return null;
            }
        }

        final int slot = (int) (readIndex & mask);
        final T e = (T) buff[slot];
        buff[slot] = null; //gc
        indexBarrier.setRelease(consumer, readIndex+1);
        return e;

    }
}
