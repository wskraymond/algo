package com.mine;

import com.mine.jpm.CountBinarySubstrings;
import com.mine.jpm.DecodeWay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCountBinarySubstrings {
    private static CountBinarySubstrings sol = new CountBinarySubstrings();
    @Test
    public void testCase1(){
        assertEquals(0, sol.countBinarySubstrings("1"));
        assertEquals(0, sol.countBinarySubstrings("0"));

        assertEquals(0, sol.countBinarySubstrings("1111"));
        assertEquals(0, sol.countBinarySubstrings("0000"));

        assertEquals(2, sol.countBinarySubstrings("1100"));
        assertEquals(2, sol.countBinarySubstrings("0011"));


        assertEquals(6, sol.countBinarySubstrings("00110011"));
        assertEquals(4, sol.countBinarySubstrings("10101"));
    }
}
