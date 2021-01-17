package com.mine;

import com.mine.math.ReverseInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestReverseInteger {
    private static ReverseInteger sol = new ReverseInteger();

    @Test
    public void testCase1(){
        assertEquals(321, sol.play(123));
        assertEquals(-321, sol.play(-123));
        assertEquals(-1, sol.play(-100));
        assertEquals(1, sol.play(100));
        assertEquals(2, sol.play(2));
        assertEquals(0, sol.play(0));
    }
}
