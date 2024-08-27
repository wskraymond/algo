package com.mine;

import com.mine.math.cyclic.FractiontoRecurringDecimal_edge_cases;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFractiontoRecurringDecimal {
    private static FractiontoRecurringDecimal_edge_cases sol = new FractiontoRecurringDecimal_edge_cases();
    /**
     * 1) dividend=5 , divisor = 2  => 2.5 => Str: 2.5
     * 2) dividend=1 , divisor = 3  => 0.3333333 => Str: 0.(3)   //cyclic decimal -> 3
     * 3) dividend=1 , divisor = 6  => 0.1666666 => Str: 0.1(6)  //cyclic decimal -> 6
     * 4) dividend=1 , divisor = 100  => 0.01 => Str: 0.01
     * 5) dividend=1 , divisor = 7  => 0.142857142857 => Str: 0.(142857)
     * 6) dividend=1 , divisor = 70  => 0.0142857142857 => Str: 0.0(142857)
     * 8) dividend=71 , divisor = 7  => 10.142857142857 => Str: 10.(142857)
     */

    @Test
    public void testCase1(){
        assertEquals("2.5", sol.fractionToDecimal(5,2));
        assertEquals("0.(3)", sol.fractionToDecimal(1,3));
        assertEquals("0.1(6)", sol.fractionToDecimal(1,6));
        assertEquals("0.01", sol.fractionToDecimal(1,100));
        assertEquals("0.(142857)", sol.fractionToDecimal(1,7));
        assertEquals("0.0(142857)", sol.fractionToDecimal(1,70));
        assertEquals("10.(142857)", sol.fractionToDecimal(71,7));
    }
}
