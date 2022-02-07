package com.mine.subsets;

import java.util.*;

public class Subsets {
    /**
     * https://leetcode.com/problems/subsets/
     *
     * Given an integer array nums of unique elements, return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
     *     All the numbers of nums are unique.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        /**
         * https://www.baeldung.com/java-list-unsupported-operation-exception
         *
         * Collections.EMPTY_LIST
         * Or Arrays.asList() are immutable
         * (doesn't implement the add() operation)
         *
         * UnsupportedOperationException is thrown from AbstractList
         */
//        result.add(Collections.EMPTY_LIST);
        result.add(new ArrayList<>());

        for(int num:nums){
            List<List<Integer>> copy = new ArrayList<>();
            for(List<Integer> l:result){
                //shallow copy of list
                copy.add(new ArrayList<>(l));
                l.add(num);
            }
            result.addAll(copy);
        }

        return result;
    }
}
