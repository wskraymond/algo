package com.concurrency.dsa;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/** The inner unlock must NOT release the lock: the second increment is still protected. */
@JCStressTest
@Outcome(id = "4", expect = Expect.ACCEPTABLE, desc = "all increments applied")
@Outcome(expect = Expect.FORBIDDEN, desc = "inner unlock released the lock early")
@State
public class ArraySpinLockReentrantTest {
    private final ArraySpinLock lock = new ArraySpinLock(2);
    private int x;

    private void nestedIncrements() {
        lock.lock();
        try {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock(); // inner unlock
            }
            x++; // still inside the outer lock
        } finally {
            lock.unlock();
        }
    }

    @Actor
    public void actor1() {
        nestedIncrements();
    }

    @Actor
    public void actor2() {
        nestedIncrements();
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = x;
    }
}
