package com.practice.fundamental;

import java.util.Objects;

public class Vector_classic {
    private float x;
    private float y;

    public Vector_classic(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /*
     * In Summary
     * Not using Float.compare() may lead to imprecise equality comparisons that don’t properly handle edge cases
     * (like NaN or signed zero) or violate transitivity when a tolerance is introduced.
     * Not using Float.hashCode(), or an equivalent robust method, can result in a hash function that doesn't reflect the true state of the floats,
     * leading to high collision rates and performance degradation.
     *
     * Both issues together can break the fundamental contract required by hash-based collections,
     * creating unpredictable behavior and potential bugs.
     *
     * Further Thoughts
     * If you ever need to work with approximate equality (using tolerance) in numerical applications,
     * consider whether objects really should be keys in a HashMap,
     * or if you could use a different data structure
     * or adjust your model. In cases where approximate equality is needed,
     * you’ll have to design both equals() and hashCode() very carefully to ensure consistency—a nontrivial challenge.
     *
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector_classic vector = (Vector_classic) o;
        return Float.compare(x, vector.x) == 0 && Float.compare(y, vector.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
