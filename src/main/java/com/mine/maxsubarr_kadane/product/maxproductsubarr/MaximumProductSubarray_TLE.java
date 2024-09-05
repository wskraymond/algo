package com.mine.maxsubarr_kadane.product.maxproductsubarr;

import java.util.stream.IntStream;

public class MaximumProductSubarray_TLE {
    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * Given an integer array nums, find a
     * subarray
     *  that has the largest product, and return the product.
     *
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     *
     * Input: nums = [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 104
     * -10 <= nums[i] <= 10
     * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        /*
            subproblem: nums[i:j]
                subarray that has the largest product, and return the product
            recurrence relations:
                f(i,j) = nums[i] * f(i+1,j)
            Base case:
                f(i,j=i) = nums[i]
            Goal:
                max = Max{f(i,j)}
         */
        if(nums==null || nums.length==0){
            return 0;
        }

        final int n = nums.length;
        int[][] dp = new int[n][n];
        int max = IntStream.range(0,n).map(i->{
            dp[i][i]=nums[i];
            return nums[i];
        }).max().getAsInt();
        for(int size=2;size<=n;size++){
            for(int i=0,j=i+size-1;j<n;j=++i+size-1){
                dp[i][j]=nums[i]*dp[i+1][j];
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
