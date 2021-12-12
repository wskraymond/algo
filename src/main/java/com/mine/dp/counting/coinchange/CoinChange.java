package com.mine.dp.counting.coinchange;

public class CoinChange {
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
        Integer[] dp = new Integer[amount+1];
        return coinChange(coins, amount, dp);
    }

    private int coinChange(int[] coins, int amount, Integer[] dp) {
        /**
         * s = amount
         * Recurrence Relation
         *      f(s) = min(f(s-coin) + 1 | for any coin in coins)
         *
         * Base case:
         *      f(0) = 0; //valid combination
         *      f(s<0) = -1; //valid combination
         *
         */


        if(amount<0){
            return -1;
        }

        if(amount==0){
            return 0;
        }

        if(dp[amount]!=null){
            return dp[amount];
        }

        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            int count = coinChange(coins, amount -coin, dp);
            if(count<0){
                continue;
            }
            min = Math.min(min, count+1);
        }

        dp[amount] = min==Integer.MAX_VALUE ? -1 : min;

        return dp[amount];
    }
}
