package com.mine;

import com.mine.dp.Knapsack01;
import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestBoatsToSavePpl {
    private static BoatsToSavePpl sol = new BoatsToSavePpl();
    @Test
    public void testCase1(){
        assertEquals(5, sol.numRescueBoats(new int[]{1, 1, 1, 1, 1} , 1));
        assertEquals(3, sol.numRescueBoats(new int[]{1, 1, 1, 1, 1} , 2));
        assertEquals(3, sol.numRescueBoats(new int[]{5, 4, 3, 2, 1} , 5));
    }
}
