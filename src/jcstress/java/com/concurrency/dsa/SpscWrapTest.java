package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.III_Result;

@JCStressTest
@Outcome(id = {"0, 0, 0", "1, 0, 0", "0, 1, 0", "0, 0, 1",
        "1, 2, 0", "1, 0, 2", "0, 1, 2", "1, 2, 3"},
        expect = Expect.ACCEPTABLE)
@Outcome(expect = Expect.FORBIDDEN)
@State
public class SpscWrapTest {

    private final SPSCRingBuffer<Integer> q = new SPSCRingBuffer<>(2);

    @Actor
    public void producer() {
        for (int i = 1; i <= 3; i++) {
            for (int spin = 0; spin < 50 && !q.offer(i); spin++) { }
        }
    }


    @Actor
    public void consumer(III_Result r) {
        r.r1 = take();
        r.r2 = take();
        r.r3 = take();
    }

    private int take() {
        Integer v = q.poll();
        return v == null ? 0 : v;
    }
}

