package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.linear.longestconsecutivesequence.LongestConsecutiveSequence_sort_nlogn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestConsecutiveSequence {
    private static LongestConsecutiveSequence_sort_nlogn sol = new LongestConsecutiveSequence_sort_nlogn();
    private static LongestConsecutiveSequence_sort_nlogn sol2 = new LongestConsecutiveSequence_sort_nlogn();
    @Test
    public void testCase1(){
        assertEquals(0, sol.longestConsecutive(new int[]{}));
        assertEquals(1, sol.longestConsecutive(new int[]{1, 1, 1, 1, 1}));
        assertEquals(4, sol.longestConsecutive(new int[]{1, 2, 3, 4, 1}));
        assertEquals(4, sol.longestConsecutive(new int[]{100,4,200,1,3,2}));
        assertEquals(9, sol.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    @Test
    public void testCase2(){
        assertEquals(0, sol2.longestConsecutive(new int[]{}));
        assertEquals(1, sol2.longestConsecutive(new int[]{1, 1, 1, 1, 1}));
        assertEquals(4, sol2.longestConsecutive(new int[]{1, 2, 3, 4, 1}));
        assertEquals(4, sol2.longestConsecutive(new int[]{100,4,200,1,3,2}));
        assertEquals(9, sol2.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
