package com.practice;

import java.util.Arrays;

public class CoinChange2 {
    /**
     * https://leetcode.com/problems/coin-change-2/discuss/99239/C-DFS-with-memorization-of-course-DP-is-better
     *
     * https://leetcode.com/problems/coin-change-2/
     *
     * You are given an integer array coins
     *      representing coins of different denominations
     *      and an integer amount representing a total amount of money.
     *
     * Return the number of combinations that make up that amount.
     *      If that amount of money cannot be made up by any combination of the coins,
     *      return 0.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     * The answer is guaranteed to fit into a signed 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: amount = 5, coins = [1,2,5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     * Example 2:
     *
     * Input: amount = 3, coins = [2]
     * Output: 0
     * Explanation: the amount of 3 cannot be made up just with coins of 2.
     *
     * Example 3:
     *
     * Input: amount = 10, coins = [10]
     * Output: 1
     *
     *
     *
     * Constraints:
     *
     *     1 <= coins.length <= 300
     *     1 <= coins[i] <= 5000
     *     All the values of coins are unique.
     *     0 <= amount <= 5000
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        /*
            sub-problem; suffix[i:]
            recurrence relation:
                f(i, k) = f(i, k-nums[i]) + f(i+1, k)
            base:
                f(i,0) = 1
                f(n,k) = 0
            goal:
                f(0,S)
            top order:
                for i=n-1....0
                    for k=1.....S
            Time: O(k*n)
            space: O(k*n) or (k)
         */
        final int n = coins.length, s=amount;
        int[] dp = new int[s+1];
        Arrays.fill(dp, 0);
        dp[0]=1;
        for(int i=n-1;i>=0;i++){
            for(int k=1;k<=s;k++){
                dp[k] += k>=coins[i] ? dp[k-coins[i]] : 0;
            }
        }
        return dp[s];
    }
}
