package com.practice;

import java.util.*;

public class SubSet2 {
    /**
     * Given an integer array nums that may contain duplicates, return all possible
     * subsets
     * (the power set).
     *
     * The solution set must not contain duplicate subsets.
     * Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 10
     *     -10 <= nums[i] <= 10
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /*
            1. The solution set must not contain duplicate subsets
            2. Return the solution in any order
         */
        /*
            map.merge()
            If the specified key is not already associated with a value
            or is associated with null, associates it with the given non-null value.
            Otherwise, replaces the associated value with the results of the given remapping function,
            or removes if the result is nul
         */


        return null; //O(n*(2^n)) + O(2*n)
    }

    private void backtrack(int i, int[] keys, Map<Integer, Integer> m, Deque<Integer> subSet, List<List<Integer>> result){

        //copy = O(n)
        //number of subset = O(2^n)
        //total = O(n*(2^n))
    }
}
