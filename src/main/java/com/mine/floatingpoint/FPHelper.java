package com.mine.floatingpoint;

public class FPHelper {
    public static boolean nearlyEqual(float a, float b, float epsilon) {
        final float absA = Math.abs(a);
        final float absB = Math.abs(b);
        final float diff = Math.abs(a - b);

        if (a == b) { // shortcut, handles infinities
            return true;
        } else if (a == 0 || b == 0 || (absA + absB < Float.MIN_NORMAL)) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (epsilon * Float.MIN_NORMAL);
        } else { // use relative error
            return diff / Math.min((absA + absB), Float.MAX_VALUE) < epsilon;
        }
    }
}
