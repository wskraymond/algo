package com.practice.conc.lockfree;

import java.util.concurrent.atomic.AtomicReference;

/**
 * NumberRange could "not" be implemented safely with
 *      - a volatile reference to an immutable holder object for the upper and lower bounds,
 *      - nor with using atomic integers to store the bounds.
 *
 * => Because an invariant constrains the two numbers
 *      and they cannot be updated simultaneously while preserving the invariant,
 *
 * => a number range class using volatile references or multiple atomic integers will have unsafe check‐then‐act sequences
 *
 * => instead, use an AtomicReference to an immutable pair of numbers, and use CAS to update both numbers atomically.
 */
public class LockFreeNumRangeChecker {
    private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower();
    }

    public int getUpper() {
        return values.get().upper();
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();

            if (i > oldv.upper()) {
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            }

            IntPair newv = new IntPair(i, oldv.upper());
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }

    // similarly for setUpper
}

record IntPair(int lower, int upper) { }
