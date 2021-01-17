package com.mine;

import com.mine.ms.DivisionToString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDivisionToString {
    private static DivisionToString sol = new DivisionToString();
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
        assertEquals("2.5", sol.play(5,2));
        assertEquals("0.(3)", sol.play(1,3));
        assertEquals("0.1(6)", sol.play(1,6));
        assertEquals("0.01", sol.play(1,100));
        assertEquals("0.(142857)", sol.play(1,7));
        assertEquals("0.0(142857)", sol.play(1,70));
        assertEquals("10.(142857)", sol.play(71,7));
    }
}
