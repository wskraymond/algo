package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.twopointers.twosum.TwoSumSorted;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestTwoSumSorted {
    private static TwoSumSorted sol = new TwoSumSorted();
    @Test
    public void testCase1(){
        // let 8 + 9 = 17 be the target
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 be the pool
        // and then eliminate possible solution(i.e 7) other than 8 + 9
        // 1, 2, 3, 4, 5, 6, 8, 9, 10
        assertArrayEquals(new int[]{7,8}, sol.twoSum(new int[]{1, 2, 3, 4, 5, 6, 8, 9, 10} , 8 + 9));

        // let 5 + 6 = 11 be the target
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 be the pool
        // and then eliminate possible solution(i.e 7) other than 5 + 6
        // 2, 4, 5, 6, 8, 10
        assertArrayEquals(new int[]{3,4}, sol.twoSum(new int[]{2, 4, 5, 6, 8, 10} , 5+6));

        assertArrayEquals(new int[]{1,2}, sol.twoSum(new int[]{2,7,11,15} , 9));
        assertArrayEquals(new int[]{1,3}, sol.twoSum(new int[]{2,3,4} , 6));
        assertArrayEquals(new int[]{1,2}, sol.twoSum(new int[]{-1,0} , -1));
    }
}
