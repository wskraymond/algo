package com.mine.dp.knapsack.unbound.coinchange2;

import java.util.Arrays;

public class CoinChange2_1d {
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
        /**
         * Idea:
         *  - dfs/backtrack for a combination nCr
         *      - not quit so ....becos 1 + 1 + 1 + 2 = 5 , 1 is duplicate number
         *  - duplicate number/coin is allowed in a combination
         *      - use it repeatedly until going to another coin
         *          - traverse by suffix[i:] (or prefix[:i]) to make sure previous coins won't be reused
         *              - e.g coins[1, 2, 5]
         *                  - 1+.....+1+ 2+....+2+ 5+....+5 in a particular sequence
         *                  - X Ones , Y twos , Z fives   for 0<=(X, Y, Z)
         * subproblem:
         *      suffix[i:] with sum = amount
         *
         * Recurrence Relations:
         *  f(i, k) = f(i, k - coins[i]) + f(i+1, k) //suffix way: i+1 , prefix way: i-1
         *
         * Base Case:
         *  f(i,0) = 1
         *
         * Goal:
         *  f(0,S) //suffix[0:]
         */

        final int n = coins.length, s=amount;
        int[][] dp = new int[n][s+1];
        Arrays.stream(dp).forEach(arr->arr[0]=1);

        for(int i=n-1;i>=0;i--){
            // can't start from j=coins[i], becos in 2D array way, you still need to clone from suffix[i+1:]
            for(int j=1;j<=s;j++){
                if(coins[i]<=j){
                    dp[i][j] +=dp[i][j-coins[i]];
                }

                if(i+1<n){
                    dp[i][j] +=dp[i+1][j];
                }
            }
        }

        return dp[0][s];
    }
}
