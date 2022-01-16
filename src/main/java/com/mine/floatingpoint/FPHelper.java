package com.mine.floatingpoint;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FPHelper {
    private static final int DEF_DIV_SCALE = 10;
    private static final RoundingMode BANKER_MODE = RoundingMode.HALF_EVEN;
    private static final MathContext DEFAULT_SETTING = new MathContext(5, BANKER_MODE);

    public static double add(double v1,double v2){
        return BigDecimal.valueOf(v1).add(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double sub(double v1,double v2){
        return BigDecimal.valueOf(v1).subtract(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double mul(double v1,double v2){
        return BigDecimal.valueOf(v1).multiply(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double div(double v1,double v2, int scale){
        if(scale<0){
            throw new IllegalArgumentException();
        }

        //banker’s rounding
        return BigDecimal.valueOf(v1).divide(BigDecimal.valueOf(v2), scale, BANKER_MODE).doubleValue();
    }

    public static double round(double v){
//      Alternative: BigDecimal.valueOf(v).setScale(5, BANKER_MODE);
        return BigDecimal.valueOf(v).round(DEFAULT_SETTING).doubleValue();
    }

    public static boolean equals(double v1, double v2){
        /**
         * BigDecimal::equal only if they are equal in value and scale
         * (thus 2.0 is not equal to 2.00 when compared by this method)
         */
        return BigDecimal.valueOf(v1).compareTo(BigDecimal.valueOf(v2))==0;
    }

    public static int compare(double v1, double v2){
        return BigDecimal.valueOf(v1).compareTo(BigDecimal.valueOf(v2));
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
