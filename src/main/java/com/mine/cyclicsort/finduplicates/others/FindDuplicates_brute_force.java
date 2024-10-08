package com.mine.cyclicsort.finduplicates.others;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates_brute_force {
    /**
     * https://leetcode.com/problems/find-all-duplicates-in-an-array/
     *
     * Given an integer array nums of length n
     * where all the integers of nums are in the range [1, n]
     * and each integer appears once or twice,
     * return an array of all the integers that appears twice.
     *
     * You must write an algorithm that runs in O(n) time and uses only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [2,3]
     * Example 2:
     *
     * Input: nums = [1,1,2]
     * Output: [1]
     * Example 3:
     *
     * Input: nums = [1]
     * Output: []
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 105
     * 1 <= nums[i] <= n
     * Each element in nums appears once or twice.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        //this approach only works if and only if numbers occur once or twice
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]) {
                    result.add(nums[i]);
                }
            }
        }

        return result;
    }
}
