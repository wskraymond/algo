package com.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        List<List<Integer>> result = new LinkedList<>();
        dfs(0,nums, new ArrayList<>(nums.length), result)
        return result;
    }

    public void dfs(int r, int[] nums, List<Integer> subset, List<List<Integer>> result){
        result.add(new ArrayList(subset));
        for(int i=r;i<nums.length;i++){
            subset.add(nums[i]);
            dfs(i+1, nums, subset,result);
            subset.remove(subset.size()-1);
        }
    }
}
