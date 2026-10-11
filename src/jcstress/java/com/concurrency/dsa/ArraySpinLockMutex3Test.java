package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

@JCStressTest
@Outcome(id = "3", expect = Expect.ACCEPTABLE, desc = "all increments applied")
@Outcome(expect = Expect.FORBIDDEN, desc = "lost update: mutual exclusion broken")
@State
public class ArraySpinLockMutex3Test {
    private final ArraySpinLock lock = new ArraySpinLock(4); // capacity >= actors
    private int x; // plain on purpose

    @Actor
    public void actor1() {
        lock.lock();
        try {
            x++;
        } finally {
            lock.unlock();
        }
    }

    @Actor
    public void actor2() {
        lock.lock();
        try {
            x++;
        } finally {
            lock.unlock();
        }
    }

    @Actor
    public void actor3() {
        lock.lock();
        try {
            x++;
        } finally {
            lock.unlock();
        }
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = x;
    }
}
