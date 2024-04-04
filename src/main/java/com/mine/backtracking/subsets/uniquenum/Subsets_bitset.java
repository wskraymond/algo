package com.mine.backtracking.subsets.uniquenum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets_bitset {
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
        for(int set=0;set<(1<<nums.length);set++){
            List<Integer> sub = new ArrayList<>(Integer.bitCount(set));
            for(int j=0;j<nums.length;j++){
                if((set&(1<<j))!=0){
                    sub.add(nums[j]);
                }
            }
            result.add(sub);
        }
        return result;
    }
}
