package com.mine.dp.buysellstock;

public class BuySellAtMostTwo {
    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     *
     * Example 2:
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     *
     * Example 3:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * Example 4:
     *
     * Input: prices = [1]
     * Output: 0
     *
     *
     *
     * Constraints:
     *
     *     1 <= prices.length <= 105
     *     0 <= prices[i] <= 105
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /**
         * f(i,2,0) = Max{f(i-1,2,0), f(i-1,2,1) + prices[i]}
         *
         * f(i,2,1) = Max{f(i-1,2,1), f(i-1,1,0) - prices[i]}
         *
         * f(i,1,0) = Max{f(i-1,1,0), f(i-1,1,1) + prices[i]}
         *
         * f(i,1,1) = Max{f(i-1,1,1), f(i-1,0,0) - prices[i]}
         *
         * Base cases:
         *  f(-1,2,0) = 0
         *  f(-1,2,1) = -Inf
         *  f(-1,1,0) = 0
         *  f(-1,1,1) = -Inf
         *  f(i, 0, 0) = 0
         *
         *  TopOrder:
         *      for i=0.....n-1
         *          for k=2...0
         *              for pos=0..1
         */

        //0 <= prices[i] <= 105
        //Alternatively,
        //1. we can set to -106
        //2. Or, becos only addition is involved , dp_k2_pos1 + prices[i] And dp_k1_pos1 + prices[i]
        //      , Integer.MIN_VALUE can be used without overflow due to computation
        //final int NEG_INF = (int) -1e9;
        final int NEG_INF = Integer.MIN_VALUE;
        int dp_k2_pos0 = 0 , dp_k2_pos1 = NEG_INF;
        int dp_k1_pos0 = 0, dp_k1_pos1 = NEG_INF;
        for(int i=0; i<prices.length;i++){
            dp_k2_pos0 = Math.max(dp_k2_pos0, dp_k2_pos1 + prices[i]);
            dp_k2_pos1 = Math.max(dp_k2_pos1, dp_k1_pos0 - prices[i]);

            dp_k1_pos0 = Math.max(dp_k1_pos0, dp_k1_pos1 + prices[i]);
            dp_k1_pos1 = Math.max(dp_k1_pos1, -prices[i]);
        }

        return dp_k2_pos0;
    }
}
