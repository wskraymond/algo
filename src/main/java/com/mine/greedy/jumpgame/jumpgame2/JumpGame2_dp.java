package com.mine.greedy.jumpgame.jumpgame2;

import java.util.Arrays;

public class JumpGame2_dp {
    /**
     * You are given a 0-indexed array of integers nums of length n.
     * You are initially positioned at nums[0].
     *
     * Each element nums[i] represents the maximum length of a forward jump from index i.
     * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     *
     * 0 <= j <= nums[i] and
     * i + j < n
     *
     * Return the minimum number of jumps to reach nums[n - 1].
     *
     * The test cases are generated such that you can reach nums[n - 1].
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: nums = [2,3,0,1,4]
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     * It's guaranteed that you can reach nums[n - 1].
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        /*
            sub-problem:
                prefix[:i]
            Recurrence Relation:
                g(i) = Min{ f(j) + 1 iff f(j)>>0 && j+nums[j]>=i
                                | for j=0...i-1
                            , n
                           }   //n : unreachable from index zero
            Base Case:
                f(0) = 0
            Goal:
                f(n-1)
         */

        final int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(j+nums[j]>=i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n-1];
    }
}
