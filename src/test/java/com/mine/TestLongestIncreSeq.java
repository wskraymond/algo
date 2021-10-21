package com.mine;

import com.mine.dp.longestincreseq.LongestIncreSeq3;
import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TestLongestIncreSeq {
    private static LongestIncreSeq3 sol = new LongestIncreSeq3();
    @Test
    public void testCase1(){
        assertEquals(4, sol.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        assertEquals(4, sol.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        assertEquals(1, sol.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }

    @Test
    public void testCase2(){
        int[] actual = sol.optimalSol(new int[]{10,9,2,5,3,7,101,18});
        Arrays.stream(actual).forEach(System.out::println);
        List<int[]> expected = new LinkedList<>();
        expected.add(new int[]{2,3,7,101});
        expected.add(new int[]{2,5,7,101});

        assertTrue(expected.stream().anyMatch(e->Arrays.equals(e, actual)));
    }
}
