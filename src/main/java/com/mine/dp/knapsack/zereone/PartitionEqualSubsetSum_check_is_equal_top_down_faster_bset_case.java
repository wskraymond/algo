package com.mine.dp.knapsack.zereone;

import java.util.Arrays;

public class PartitionEqualSubsetSum_check_is_equal_top_down_faster_bset_case {
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
        int sum = Arrays.stream(nums).sum();
        int remain = sum%2;
        if(remain!=0){
            return false;
        }

        int s = sum/2;
        /*
            - f(i,j) = f(i+1,j-num[i]) || f(i+1,j)
            - f(i,0) = true, f(i, j<0) = false , f(n, j>0) = false
         */
        Boolean[][] memo = new Boolean[nums.length][s+1];
        return dfs(0, s , nums, memo);
    }

    private boolean dfs(int i, int j, int[] nums, Boolean[][] memo){
        if(j==0){
            return true;
        }

        if(i==nums.length || j<0){
            return false;
        }

        if(memo[i][j]!=null){
            return memo[i][j];
        }

        memo[i][j]=dfs(i+1,j-nums[i], nums, memo) || dfs(i+1,j, nums, memo);
        return memo[i][j];
    }
}
