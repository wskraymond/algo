package com.mine.dp.knapsack.zereone;

import java.util.Arrays;

public class PartitionEqualSubsetSum_check_max {
    /**
     * Given a non-empty array nums containing only positive integers,
     * find if the array can be partitioned into two subsets
     *      such that the sum of elements in both subsets is equal.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,5,11,5]
     * Output: true
     * Explanation: The array can be partitioned as [1, 5, 5] and [11].
     * Example 2:
     *
     * Input: nums = [1,2,3,5]
     * Output: false
     * Explanation: The array cannot be partitioned into equal sum subsets.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int remain = sum%2;
        if(remain!=0){
            return false;
        }

        int s = sum/2;
        int[][] dp = new int[n+1][s+1];
        for(int i=n-1;i>=0;i--){
            for(int j=1;j<=s;j++){
                dp[i][j] = dp[i+1][j];
                if(nums[i]<=j){
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j-nums[i]] + nums[i]);
                }
            }
        }

        return dp[0][s]==s;
    }
}
