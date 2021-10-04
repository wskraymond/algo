package com.mine.twopointers.threeSum;

import java.util.Arrays;

public class ThreeSumSmaller {
    /**
     * Given an array of n integers nums and an integer target,
     * find the number of index triplets i, j, k
     * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [-2,0,1,3], target = 2
     * Output: 2
     * Explanation: Because there are two triplets which sums are less than 2:
     * [-2,0,1]
     * [-2,0,3]
     *
     * Example 2:
     *
     * Input: nums = [], target = 0
     * Output: 0
     *
     * Example 3:
     *
     * Input: nums = [0], target = 0
     * Output: 0
     *
     *
     *
     * Constraints:
     *
     *     n == nums.length
     *     0 <= n <= 3500
     *     -100 <= nums[i] <= 100
     *     -100 <= target <= 100
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        //with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target
        //it means one number could not be used more than once
        //and sum of 3 < target
        Arrays.sort(nums);

        int result = 0;
        for(int i=0;i<nums.length;i++){
            result+=twoSumSmaller(nums, i, target);
        }

        return result;
    }

    public int twoSumSmaller(int[] nums, int firstIndex, int target){
        int i = firstIndex + 1;
        int j = nums.length - 1;
        int first = nums[firstIndex];

        int result = 0;
        while(i<j){
            if(first + nums[i] + nums[j] >= target){
                j--;
            } else {
                result+=j-i;
                i++;
            }
        }

        return result;
    }
}
