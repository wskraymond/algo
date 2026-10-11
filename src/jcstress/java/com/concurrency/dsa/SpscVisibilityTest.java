package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE, desc = "consumer has not seen the message yet")
@Outcome(id = "1, 42", expect = Expect.ACCEPTABLE, desc = "saw the complete message")
@Outcome(expect = Expect.FORBIDDEN, desc = "saw a partially written message")
@State
public class SpscVisibilityTest {

    static final class Msg {
        int a;
        int b;
    }

    private final SPSCRingBuffer<Msg> q = new SPSCRingBuffer<>(4);

    @Actor
    public void producer() {
        Msg m = new Msg();
        m.a = 1;
        m.b = 42;
        q.offer(m);
    }

    @Actor
    public void consumer(II_Result r) {
        Msg m = q.poll();
        if (m != null) {
            r.r1 = m.a;
            r.r2 = m.b;
        }
    }
}
