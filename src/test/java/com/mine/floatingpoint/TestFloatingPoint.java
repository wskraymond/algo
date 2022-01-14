package com.mine.floatingpoint;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class TestFloatingPoint {
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
        System.out.println(new BigDecimal(.1d));    //0.1000000000000000055511151231257827021181583404541015625
        System.out.println(format.format(new BigDecimal(.1d).doubleValue())); //0.1000000000000000000000000000000000000000000000000000000
        assertFalse(new BigDecimal(.1d).add(new BigDecimal(.2d)).equals(new BigDecimal("0.3")));
        /**
         * If double must be used for initializing a BigDecimal,
         * use BigDecimal.valueOf(double),
         * which converts the Double value to String using Double.toString(double) method
         */
        System.out.println(BigDecimal.valueOf(.1d));    //0.1
        System.out.println(new BigDecimal("0.1"));  //0.1
        System.out.println(format.format(new BigDecimal("0.1").doubleValue())); //0.1000000000000000000000000000000000000000000000000000000
        assertTrue(BigDecimal.valueOf(.1d).add(BigDecimal.valueOf(.2d)).equals(new BigDecimal("0.3")));
    }

    @Test
    public void testArithmetic(){
        System.out.println(0.05+0.01);

        System.out.println(1.0-0.42);

        System.out.println(4.015*100);

        System.out.println(123.3/100);
    }

    @Test
    public void testRoundUp(){
        System.out.println(4.015d);
        System.out.println(4.015d*100.0);               //401.49999999999994
        System.out.println(Math.round(4.015d*100.0));  //401
        double actual = roundUp.round(4.015);    //4.01
        double expected = Double.valueOf("4.02");
        System.out.println(actual);
        System.out.println(expected);
        assertFalse(Double.compare(expected, actual)==0);
    }

    @Test
    public void testFormater(){
        String actual = roundUp.roundToStr(4.015);
        String expected = "4.02";
        System.out.println(actual);
        System.out.println(expected);
        assertNotEquals(expected, actual);
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
    }


}
