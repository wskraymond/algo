package com.mine.maxsubarr_kadane.sum.maxsubarray;


/**
 * https://leetcode.com/problems/maximum-subarray/
 * 
 * Given an integer array nums,
 * find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

    public int play(int[] arr){
        int tmp = 0;
        int max = arr[0];
        for(int i=1;i<arr.length;i++){
            if(tmp + arr[i] > 0) {
                tmp += arr[i];
                if (tmp > max){
                    max = tmp;
                }
            } else {
                tmp =0;
                if(arr[i] >max)
                    max = arr[i];
            }
        }

        return max;
    }
}
