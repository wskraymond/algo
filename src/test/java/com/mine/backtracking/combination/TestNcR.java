package com.mine.backtracking.combination;

import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNcR {
    private static NcR sol = new NcR();
    @Test
    public void testCase1(){
        assertEquals(1, sol.nCr(5, 0));
        assertEquals(5, sol.nCr(5, 1));
        assertEquals(10, sol.nCr(5, 2));
        assertEquals(10, sol.nCr(5, 3));
        assertEquals(5, sol.nCr(5, 4));
        assertEquals(1, sol.nCr(5, 5));
    }

    @Test
    public void testCase2(){
        assertEquals(20, sol.nCr(6, 3));
    }
}
