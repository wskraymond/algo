package com.mine;

import com.mine.math.ReverseInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestReverseInteger {
    private static ReverseInteger sol = new ReverseInteger();

    @Test
    public void testCase1(){
        assertEquals(321, sol.reverse(123));
        assertEquals(-321, sol.reverse(-123));
        assertEquals(-1, sol.reverse(-100));
        assertEquals(1, sol.reverse(100));
        assertEquals(2, sol.reverse(2));
        assertEquals(0, sol.reverse(0));
    }
}
