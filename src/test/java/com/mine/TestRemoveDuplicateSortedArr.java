package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.twopointers.RemoveDuplicatesSortedArr;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRemoveDuplicateSortedArr {
    private static RemoveDuplicatesSortedArr sol = new RemoveDuplicatesSortedArr();

    @Test
    public void testCase1(){
        int[] actual = new int[]{0,0, 1,1, 1,2,2};
        verify(actual, sol.removeDuplicates(actual), new int[]{0,1,2});
    }

    @Test
    public void testCase2(){
        int[] actual = new int[]{0};
        verify(actual, sol.removeDuplicates(actual), new int[]{0});
    }

    @Test
    public void testCase3(){
        int[] actual = new int[]{0, 0, 0};
        verify(actual, sol.removeDuplicates(actual), new int[]{0});
    }

    @Test
    public void testCase4(){
        int[] actual = new int[]{0, 1, 2};
        verify(actual, sol.removeDuplicates(actual), new int[]{0, 1, 2});
    }

    private void verify(int[] actual, int length, int[] expected){
        assertEquals(expected.length, length);

        for(int i=0;i<expected.length; i++){
            assertEquals(expected[i], actual[i]);
        }
    }
}
