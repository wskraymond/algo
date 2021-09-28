package com.mine.twopointers.threeSum;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * 1) such that i != j, i != k, and j != k, //position i, j, k
     * 2) and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     *
     * Example 1:
     *
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     *
     * Example 2:
     *
     * Input: nums = []
     * Output: []
     *
     * Example 3:
     *
     * Input: nums = [0]
     * Output: []
     *
     * Constraints:
     *
     *     0 <= nums.length <= 3000
     *     -105 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();
        for(int i=0; i<nums.length;i++){

        }


    }

    public int[] twoSum(int[] numbers,int i, int j, int target) {
        target=-target;
        while(i<j){
            if(numbers[i] + numbers[j] > target){
                j--;
            } else if(numbers[i] + numbers[j] < target) {
                i++;
            } else {
                target=-target;
                return  new int[]{numbers[i], numbers[j], target};
            }
        }

        return null;
    }
}
