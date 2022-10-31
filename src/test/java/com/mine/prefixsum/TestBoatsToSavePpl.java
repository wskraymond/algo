package com.mine.prefixsum;

import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoatsToSavePpl {
    private static ContinuousSubarraySum sol = new ContinuousSubarraySum();
    @Test
    public void testCase1(){
        assertEquals(true, sol.checkSubarraySum(new int[]{23,2,4,6,7}
                , 6));
        assertEquals(true, sol.checkSubarraySum(new int[]{23,6,6,3}
                , 6));
        assertEquals(true, sol.checkSubarraySum(new int[]{23,0,0,3}
                , 6));
        assertEquals(false, sol.checkSubarraySum(new int[]{23,6,3}
                , 6));

    }
}
