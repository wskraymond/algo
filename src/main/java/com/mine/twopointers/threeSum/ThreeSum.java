package com.mine.twopointers.threeSum;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * 1) such that i != j, i != k, and j != k, //position i, j, k
     * 2) and nums[i] + nums[j] + nums[k] == 0.
     * 3) Notice that the solution set must not contain duplicate triplets.
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

        List<List<Integer>> result = new ArrayList<>();
        int i =0 ;
        while(i < nums.length){
            if(nums[i]> 0){ //impossibly sum to zero
                break;
            } //[0, 0, 0] is one of solutions.

            twoSum(nums, i++, result);

            while(i < nums.length
                    && nums[i-1] == nums[i]){
                i++;
            }
        }

        return result;
    }

    public void twoSum(int[] nums, int firstIndex, List<List<Integer>> result) {
        int first = nums[firstIndex];
        int i = firstIndex + 1;
        int j = nums.length - 1;

        while(i<j){
            if(first + nums[i] + nums[j] > 0){
                j--;
            } else if(first + nums[i] + nums[j] < 0){
                i++;
            } else {
                //add to result and then move inward for both i , j.
                result.add(Arrays.asList(first, nums[i++] , nums[j--]));
                while(i < j
                        && nums[i-1] == nums[i]){ //skip duplicate i
                    i++;
                }

                while(i < j
                        && nums[j+1] == nums[j]){ //skip duplicate j
                    j--;
                }
            }
        }
    }

}
