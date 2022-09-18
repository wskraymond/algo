package com.mine;

import com.mine.dp.knapsack.zereone.Knapsack01;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestKnapsack01 {
    private static Knapsack01 sol = new Knapsack01();
    @Test
    public void testCase1(){
        assertEquals(99, sol.optimalVal(7, new int[]{3} , new int[]{99}));
        assertArrayEquals(new boolean[]{true}, sol.optimalSol(7, new int[]{3} , new int[]{99}));
        assertEquals(10, sol.optimalVal(7, new int[]{3, 1, 3, 2, 4} , new int[]{2, 2, 4, 3, 5}));
        assertArrayEquals(new boolean[]{false, true, false, true, true}, sol.optimalSol(7, new int[]{3, 1, 3, 2, 4} , new int[]{2, 2, 4, 3, 5}));
    }
}
