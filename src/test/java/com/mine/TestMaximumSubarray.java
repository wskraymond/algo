package com.mine;

import com.mine.greedy.subarr.maxsubarray.MaximumSubarray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMaximumSubarray {
    private static MaximumSubarray sol = new MaximumSubarray();
    @Test
    public void testCase1(){
        assertEquals(6, sol.play(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        assertEquals(-1, sol.play(new int[]{-9, -2, -1, -4}));
    }
}
