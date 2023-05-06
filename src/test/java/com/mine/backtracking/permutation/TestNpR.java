package com.mine.backtracking.permutation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNpR {
    private static NpR sol = new NpR();
    @Test
    public void testCase1(){
        assertEquals(1, sol.nPr(5, 0));
        assertEquals(5, sol.nPr(5, 1));
        assertEquals(20, sol.nPr(5, 2));
        assertEquals(60, sol.nPr(5, 3));
        assertEquals(120, sol.nPr(5, 4));
        assertEquals(120, sol.nPr(5, 5));
    }

    @Test
    public void testCase2(){
        assertEquals(120, sol.nPr(6, 3));
    }
}
