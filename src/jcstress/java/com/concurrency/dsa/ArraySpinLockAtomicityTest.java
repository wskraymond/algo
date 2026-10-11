package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE, desc = "reader ran before writer")
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE, desc = "reader ran after writer")
@Outcome(expect = Expect.FORBIDDEN, desc = "reader saw a half-finished critical section")
@State
public class ArraySpinLockAtomicityTest {
    private final ArraySpinLock lock = new ArraySpinLock(2);
    private int a; // plain
    private int b; // plain

    @Actor
    public void writer() {
        lock.lock();
        try {
            a = 1;
            b = 1;
        } finally {
            lock.unlock();
        }
    }

    @Actor
    public void reader(II_Result r) {
        lock.lock();
        try {
            r.r1 = a;
            r.r2 = b;
        } finally {
            lock.unlock();
        }
    }
}
