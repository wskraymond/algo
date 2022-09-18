package com.mine.dp.knapsack.zereone;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TargetSum {
    /**
     * You are given an integer array nums and an integer target.
     *
     * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
     *
     *     For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
     *
     * Return the number of different expressions that you can build, which evaluates to target.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: 5
     * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     *
     * Example 2:
     *
     * Input: nums = [1], target = 1
     * Output: 1
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 20
     *     0 <= nums[i] <= 1000
     *     0 <= sum(nums[i]) <= 1000
     *     -1000 <= target <= 1000
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int s = Math.abs(target);
        int c = Arrays.stream(nums).sum();

        if(c < s){
            return 0;
        }

        if((c - s) % 2 !=0){
            return 0;
        }

        int b = (c-s)/2, n = nums.length;
        int[][] dp = new int[n+1][b+1];
//        IntStream.range(0, n+1).forEach(i->dp[i][0]=1);
        dp[n][0]=1;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=b;j++){
                dp[i][j] = (j-nums[i]>=0 ? dp[i+1][j-nums[i]] : 0) +
                            dp[i+1][j];
            }
        }

        return dp[0][b];
    }
}
