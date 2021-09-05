package com.mine;

import com.mine.dp.Knapsack01;
import com.mine.greedy.FractionalKnapsack;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestFractionalKnapsack {
    private static FractionalKnapsack sol = new FractionalKnapsack();
    @Test
    public void testCase1(){
        assertEquals(99d, sol.optimalVal(7, new int[]{3} , new int[]{99}), 0.000001);
        assertEquals(26.5, sol.optimalVal(23, new int[]{20, 5, 15, 5} , new int[]{10,5,5,15}), 0.000001);
    }
}
