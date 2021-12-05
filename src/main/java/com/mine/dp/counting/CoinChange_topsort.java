package com.mine.dp.counting;

import java.util.Arrays;

public class CoinChange_topsort {
    /**
     *https://www.geeksforgeeks.org/coin-change-dp-7/?ref=leftbar-rightbar
     *https://leetcode.com/problems/coin-change/
     *
     */

    /**
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
     *
     * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     *
     *
     * Example 1:
     *
     * Input: coins = [1,2,5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     * Example 3:
     *
     * Input: coins = [1], amount = 0
     * Output: 0
     * Example 4:
     *
     * Input: coins = [1], amount = 1
     * Output: 1
     * Example 5:
     *
     * Input: coins = [1], amount = 2
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     *
     */
    public int coinChange(int[] coins, int amount) {
        /**
         * s = amount
         * Recurrence Relation
         *      f(s) = min(f(s-coin) + 1 | for any coin in coins)
         *
         * Base case:
         *      f(0) = 0; //valid combination
         *      f(s<0) = -1; //invalid combination
         *
         */

        int[] dp = new int[amount+1]; //includes zero
        //min integer amoutn for a coin = 1
        //Max no of coin combination = amount
        final int AMT_MAX = amount + 1;
        Arrays.fill(dp, AMT_MAX); //to deal with overflow problem , so that we cannot use Integer.MAX_VALUE
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int coin:coins){
                if(i-coin>=0){
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);  //Integer.MAX_VALUE + 1 overflows
                }
            }
        }

        return dp[amount]==AMT_MAX ? -1 : dp[amount];
    }
}
