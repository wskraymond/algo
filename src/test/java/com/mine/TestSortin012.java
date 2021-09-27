package com.mine;

import com.mine.linear.Sorting012;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestSortin012 {
    private static Sorting012 sol = new Sorting012();

    @Test
    public void testCase1(){
        assertArrayEquals(new int[]{0}, sol.sort012(new int[]{0}));
        assertArrayEquals(new int[]{1}, sol.sort012(new int[]{1}));
        assertArrayEquals(new int[]{2}, sol.sort012(new int[]{2}));
        assertArrayEquals(new int[]{0,0,0,0,1,1,1,2,2}, sol.sort012(new int[]{0,1,0,0,1,2,2,1,0}));
    }
}
