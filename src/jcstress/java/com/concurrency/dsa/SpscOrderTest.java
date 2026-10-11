package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = {"0, 0", "0, 1", "1, 0", "1, 2"}, expect = Expect.ACCEPTABLE)
@Outcome(expect = Expect.FORBIDDEN, desc = "out-of-order, duplicated, or out-of-thin-air value")

@State
public class SpscOrderTest {

    private final SPSCRingBuffer<Integer> q = new SPSCRingBuffer<>(4);

    @Actor
    public void producer() {
        q.offer(1);
        q.offer(2);
    }

    @Actor
    public void consumer(II_Result r) {
        Integer x = q.poll();
        Integer y = q.poll();
        r.r1 = x == null ? 0 : x;
        r.r2 = y == null ? 0 : y;
    }
}

