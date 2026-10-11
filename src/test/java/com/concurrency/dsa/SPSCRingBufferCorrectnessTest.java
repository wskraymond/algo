package com.concurrency.dsa;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

public class SPSCRingBufferCorrectnessTest {

    private static final long TOTAL = 50_000_000L;

    interface Q { boolean offer(Integer e); Integer poll(); }

    private static Q spsc(int cap) {
        SPSCRingBuffer<Integer> q = new SPSCRingBuffer<>(cap);
        return new Q() {
            public boolean offer(Integer e) { return q.offer(e); }
            public Integer poll() { return q.poll(); }
        };
    }

    private static Q abq(int cap) {
        Queue<Integer> q = new LinkedList<>();
        return new Q() {
            public boolean offer(Integer e) { return q.offer(e); }
            public Integer poll() { return q.poll(); }
        };
    }

    private static void verify(String name, Q q) throws Exception {
        final long[] r = new long[3]; // received, outOfOrder, sum
        Thread consumer = new Thread(() -> {
            long expected = 0, received = 0, bad = 0, sum = 0;
            while (received < TOTAL) {
                Integer v = q.poll();
                if (v == null) { Thread.onSpinWait(); continue; }
                if (v != (int) expected) bad++;
                expected++;
                received++;
                sum += v;
            }
            r[0] = received; r[1] = bad; r[2] = sum;
        }, name + "-consumer");
        Thread producer = new Thread(() -> {
            for (int i = 0; i < TOTAL; i++) {
                while (!q.offer(i)) Thread.onSpinWait();
            }
        }, name + "-producer");

        consumer.start();
        producer.start();
        producer.join();
        consumer.join(60_000);

        assertEquals(name + " received", TOTAL, r[0]);
        assertEquals(name + " out-of-order", 0, r[1]);
        assertEquals(name + " sum", TOTAL * (TOTAL - 1) / 2, r[2]);
    }

    @Test(timeout = 120_000)
    public void spscCap2() throws Exception { verify("spsc-2", spsc(2)); }

    @Test(timeout = 120_000)
    public void spscCap16() throws Exception { verify("spsc-16", spsc(16)); }

    @Test(timeout = 120_000)
    public void spscCap1024() throws Exception { verify("spsc-1024", spsc(1024)); }

    @Test(timeout = 120_000)
    public void spscCap65536() throws Exception { verify("spsc-65536", spsc(65536)); }

    @Test(timeout = 120_000)
    public void abqReference() throws Exception { verify("abq-1024", abq(1024)); }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNonPowerOfTwo() { new SPSCRingBuffer<Integer>(1000); }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsZero() { new SPSCRingBuffer<Integer>(0); }

    @Test
    public void offerFailsWhenFullPollFailsWhenEmpty() {
        SPSCRingBuffer<Integer> q = new SPSCRingBuffer<>(4);
        assertNull(q.poll());
        for (int i = 0; i < 4; i++) assertTrue(q.offer(i));
        assertFalse(q.offer(99));
        for (int i = 0; i < 4; i++) assertEquals(Integer.valueOf(i), q.poll());
        assertNull(q.poll());
    }
}
