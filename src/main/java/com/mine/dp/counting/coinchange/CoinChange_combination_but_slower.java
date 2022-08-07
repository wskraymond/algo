package com.mine.dp.counting.coinchange;

public class CoinChange_combination_but_slower {
    /**
     *https://www.geeksforgeeks.org/coin-change-dp-7/?ref=leftbar-rightbar
     *https://leetcode.com/problems/coin-change/
     *
     */

    /**
     * You are given an integer array coins
     * representing coins of different denominations
     * and an integer amount representing a total amount of money.
     *
     * Return the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up
     * by any combination of the coins, return -1.
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
         * Idea:
         *  - dfs/backtrack for a combination nCr
         *      - not quit so ....becos 1 + 1 + 1 + 2 = 5 , 1 is duplicate number
         *      - duplicate number/coin is allowed in a combination
         *          - use it repeatedly until going to another coin
         *              - traverse by suffix[i:] (or prefix[:i]) to make sure previous coins won't be reused
         *                  - e.g coins[1, 2, 5]
         *                      - 1+.....+1+ 2+....+2+ 5+....+5 in a particular sequence
         *                      - X Ones , Y twos , Z fives   for 0<=(X, Y, Z)
         * subproblem:
         *      suffix[i:] with sum = amount
         *
         * Recurrence Relations:
         *  f(i, k) = min{ f(i, k - coins[i]) + 1,  f(i+1, k) } //suffix way: i+1 , prefix way: i-1
         *
         * Base Case:
         *  f(i,0) = 0
         *  f(n,k) = amount + 1 (invalid)
         *
         * Goal:
         *  f(0,S) //suffix[0:]
         */

        /*
        Objective: use of solution of coinChange 2 in coinChange 1

            1. solution of coinChange 1 is normally bruteforce in permutation with memo
            2. think in alternative way, what if we brute force in combination to find the min , does it work ?
            3. As this function involves 2 parameters instead of 1d array , the time complexity of dp is NxS (slower)
         */
        int result = dfs(0, amount, amount, coins, new Integer[coins.length][amount+1]);
        return result==amount+1 ? -1 : result;
    }

    private int dfs(int i, int k, int amount, int[] coins, Integer[][] dp) {
        if(k==0){
            return 0;
        }

        if(i==coins.length){
            return amount+1;
        }

        if(dp[i][k]!=null){
            return dp[i][k];
        }

        int count =amount+1;
        if(coins[i]<=k){
            count = Math.min(count, dfs(i, k-coins[i], amount, coins, dp)+1);
        }

        count = Math.min(count, dfs(i+1, k,amount, coins, dp));

        dp[i][k] = count;

        return count;
    }
}
