package com.mine.floatingpoint;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class TestFloatingPoint {
    /**
     * Native Concept:
     *      1. Literal(i.e 4.015d) is what we typed in the editor (.java file)
     *      2. But it does not mean what we stored in the memory as double is precise (exactly 4.015)
     *          when byte code is executed by JVM
     *      3. Instead, decimal representation in the editor (.java file) will be converted in binary format(double precision)
     *         , then precision will be lost due to finite binary sequence(double),
     *         it is called round off
     *      4. imprecise representation of 4.015d is called rounding error
     *      5. This is why DecimalFormat with HALF_UP rounding mode
     *         will also fail what we expect
     */
    private FPHelper_wrong roundUp = new FPHelper_wrong();
    private DecimalFormat format = new DecimalFormat("0.0000000000000000000000000000000000000000000000000000000");

    @Test
    public void testRoundingError(){
        //0.1
        System.out.println(String.valueOf(.1f));
        //0.1000000014901161200000000000000000000000000000000000000
        System.out.println(format.format(.1f));
        //0.1000000000000000000000000000000000000000000000000000000
        System.out.println(format.format(.1d));
        System.out.println(100000000f + 1); //result = 1.0E8
        System.out.println(100000000d + 1); //result = 1.00000001E8
    }

    @Test
    public void testBigDecimal(){
        /**
         * new BigDecimal(.1) is not exactly equal to .1,
         * but it is actually equal to .1000000000000000055511151231257827021181583404541015625.
         * This is so because .1 cannot be represented exactly as a double
         * (or, for that matter, as a binary fraction of any @finite@ length)
         */
        //BigDecimal(double) is unpredictable
        //due to the inability of the double to represent 0.1 as exact 0.1
        //0.1000000000000000055511151231257827021181583404541015625
        System.out.println(new BigDecimal(.1d).toString());
        //0.1000000000000000000000000000000000000000000000000000000
        System.out.println(format.format(new BigDecimal(.1d).doubleValue()));
        //0.3000000000000000400000000000000000000000000000000000000
        System.out.println(format.format(new BigDecimal(.1d).add(new BigDecimal(.2d)).doubleValue()));
        assertFalse(new BigDecimal(.1d).add(new BigDecimal(.2d)).equals(new BigDecimal("0.3")));
        /**
         * If double must be used for initializing a BigDecimal,
         * use BigDecimal.valueOf(double),
         * which converts the Double value to String using Double.toString(double) method
         */
        System.out.println(BigDecimal.valueOf(.1d).toString());    //0.1
        System.out.println(BigDecimal.valueOf(.1d).doubleValue());    //0.1
        System.out.println(new BigDecimal("0.1"));  //0.1
        System.out.println(format.format(new BigDecimal("0.1").doubleValue())); //0.1000000000000000000000000000000000000000000000000000000
        assertTrue(BigDecimal.valueOf(.1d).add(BigDecimal.valueOf(.2d)).equals(new BigDecimal("0.3")));
    }

    @Test
    public void testRoundUp(){
        System.out.println(4.015d);
        System.out.println(4.015d*100.0);               //401.49999999999994
        System.out.println(Math.round(4.015d*100.0));  //401
        System.out.println(Math.round(401.5d));  //402
        double actual = roundUp.round(4.015);    //4.01
        double expected = Double.valueOf("4.02");
        System.out.println(actual);
        System.out.println(expected);
        assertFalse(Double.compare(expected, actual)==0);
    }

    @Test
    public void testFPToDecimal(){
        float a = 0.525f;
        float b = 0.53f;

        float c = (float) FPInputWithDecimalOperationHelper_still_imprecise.round(a, 2, RoundingMode.HALF_UP);
        //0.5199999809265137000000000000000000000000000000000000000
        System.out.println(format.format(c));
        //0.5299999713897705000000000000000000000000000000000000000
        System.out.println(format.format(b));
        assertFalse(FPInputWithDecimalOperationHelper_still_imprecise.equals(c,b));
    }

    @Test
    public void testFPToDecimal2(){
        double a = 0.525;
        double b = 0.53;

        double c = FPInputWithDecimalOperationHelper_still_imprecise.round(a,2, RoundingMode.HALF_UP);
        //0.5300000000000000000000000000000000000000000000000000000
        System.out.println(format.format(c));
        //0.5250000000000000000000000000000000000000000000000000000
        System.out.println(format.format(a));
        //0.5300000000000000000000000000000000000000000000000000000
        System.out.println(format.format(b));
        assertTrue(FPInputWithDecimalOperationHelper_still_imprecise.equals(c,b));
    }

    @Test
    public void testFormatter(){
        float a = 0.525f;
        //0.5249999761581421000000000000000000000000000000000000000
        System.out.println(format.format(a));
        String actual = roundUp.roundToStr(a);
        String expected = "0.53";
        //0.52
        System.out.println(actual);
        //0.53
        System.out.println(expected);
        assertNotEquals(expected, actual);


        double b = 0.525;
        //0.5250000000000000000000000000000000000000000000000000000
        System.out.println(format.format(b));
        actual = roundUp.roundToStr(b);
        expected = "0.53";
        //0.53
        System.out.println(actual);
        //0.53
        System.out.println(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testEquality(){
        assertTrue(1f!=0f);
        assertTrue(0f==0f);
        assertTrue(0d==0d);
        assertTrue(0d==-0d);
        assertTrue(0.1d==0.1d);
        assertTrue((-1d/0d)!=1d/0d);
        assertTrue(Double.isInfinite(-1d/0d));
        assertTrue(Double.NEGATIVE_INFINITY!=Double.POSITIVE_INFINITY);
        assertTrue(0d/0d!=0d/0d);
        assertTrue(Double.isNaN(0d/0d));
        assertFalse(1f!=1f);
        assertTrue(Double.NaN!=Double.NaN);
        assertTrue(Double.NaN!=0.1d);
        assertFalse(Double.NaN==0.1d);
    }

    @Test
    public void testCompare(){
        assertFalse(0.0d>-0.0d);
        assertTrue(0.0d<=-0.0d);
        assertFalse(1d<=Double.NaN);
        assertFalse(1d>=Double.NaN);
        assertFalse(Double.NaN<=Double.NaN);
        assertFalse(Double.NaN>=Double.NaN);

        long a = Double.doubleToLongBits(0.1d);
        long b = Double.doubleToLongBits(0.1d);
        assertTrue(a==b);
        assertFalse(a!=b);

        a = Double.doubleToLongBits(0.00015f + 0.00015f);
        b = Double.doubleToLongBits(0.0001f + 0.0002f);
        assertFalse(a==b);  //failed to show the truth
        assertTrue(a!=b);

        a = Double.doubleToLongBits(-0d);
        b = Double.doubleToLongBits(0d);
        assertTrue(a < b);
        assertFalse(a > b);

        a = Double.doubleToLongBits(0.1d);
        b = Double.doubleToLongBits(Double.NaN);
        assertTrue(a < b);
        assertFalse(a > b);
    }

    @Test
    public void testArithmeticOperation(){
        System.out.println(0.05+0.01);

        System.out.println(1.0-0.42);

        System.out.println(4.015*100);

        System.out.println(123.3/100);

        float a = 0.00015f + 0.00015f;
        float b = 0.0001f + 0.0002f;
        //0.0003000000142492354000000000000000000000000000000000000
        System.out.println(format.format(a));
        //0.0002999999851454049300000000000000000000000000000000000
        System.out.println(format.format(b));
        assertFalse(Double.compare(a,b)==0);
        assertFalse(a==b);
        assertTrue(a>=b);
        assertFalse(a<=b);
    }


}
