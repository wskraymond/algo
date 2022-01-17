package com.mine.floatingpoint;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
/**
 * BigDecimal is a correct way to convert double to BigDecimal
 *      1. Given the input can be represented by double precisely enough,
 *         (The expected scale won't be so large that double cannot represent)
 *      2. Then,
 */
public class FPInputWithDecimalOperationHelper_still_imprecise {
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
    public static double div(double v1,double v2, int scale){
        if(scale<0){
            throw new IllegalArgumentException();
        }

        //banker’s rounding
        return BigDecimal.valueOf(v1).divide(BigDecimal.valueOf(v2), scale, BANKER_MODE).doubleValue();
    }

    public static double round(double v, int scale, RoundingMode mode){
//      Alternatives:
      return BigDecimal.valueOf(v).setScale(scale, mode).doubleValue();
//        return BigDecimal.valueOf(v).round(DEFAULT_SETTING).doubleValue();
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
}
