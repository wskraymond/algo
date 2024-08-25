package com.floatingpoint;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FPHelper {
    private static final int DEF_DIV_SCALE = 10;
    private static final RoundingMode BANKER_MODE = RoundingMode.HALF_EVEN;
    private static final MathContext MC = new MathContext(15, BANKER_MODE);

    public static BigDecimal valueOf(String v){
        return new BigDecimal(v);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2){
        return v1.add(v2, MC);
    }

    /**
     *  In the case of divide, the exact quotient could have an infinitely long
     *  decimal expansion;
     *  for example, 1 divided by 3
     *
     * If the quotient has a nonterminating decimal expansion and the operation is specified to return an exact result,
     * an ArithmeticException is thrown.
     *
     * Otherwise, the exact result of the division
     * is returned, as done for other operations.
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal div(BigDecimal v1,BigDecimal v2, int scale){
        if(scale<0){
            throw new IllegalArgumentException();
        }

        //banker’s rounding
        return v1.divide(v2, scale, BANKER_MODE);
    }

    public static void round(BigDecimal v, int scale, RoundingMode mode){
//      Alternatives:
        v.setScale(scale, mode);
//        return v.round(DEFAULT_SETTING);
    }

    public static int compare(double v1, double v2, double epsilon){
        return nearlyEqual(v1, v2, epsilon) ? 0 :
                v1 > v2 ? 1 : -1;
    }

    public static int compare(float v1, float v2, float epsilon){
        return nearlyEqual(v1, v2, epsilon) ? 0 :
                v1 > v2 ? 1 : -1;
    }

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

    public static boolean nearlyEqual(double a, double b, double epsilon) {
        final double absA = Math.abs(a);
        final double absB = Math.abs(b);
        final double diff = Math.abs(a - b);

        if (a == b) { // shortcut, handles infinities
            return true;
        } else if (a == 0 || b == 0 || (absA + absB < Double.MIN_NORMAL)) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (epsilon * Double.MIN_NORMAL);
        } else { // use relative error
            return diff / Math.min((absA + absB), Double.MAX_VALUE) < epsilon;
        }
    }
}
