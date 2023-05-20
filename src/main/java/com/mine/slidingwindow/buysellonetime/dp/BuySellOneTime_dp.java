package com.mine.slidingwindow.buysellonetime.dp;

public class BuySellOneTime_dp {

    /**
     * You are given an array prices
     * where prices[i] is the price
     * of a given stock on the ith day.
     *
     * You want to maximize your profit
     * by choosing a single day to buy one stock
     * and choosing a different day in the future to sell that stock.
     *
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     *
     *
     *
     * Example 1:
     *
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     *
     * Example 2:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transactions are done and the max profit = 0.
     *
     *
     * Constraints:
     *
     *     1 <= prices.length <= 105
     *     0 <= prices[i] <= 104
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /**
         * Recurrence Relations:
         *      - f(i,1, 0) = Max{f(i-1, 1, 0) , f(i-1,1, 1) + prices[i]}
         *      - f(i,1, 1) = Max{f(i-1, 1, 1) , f(i-1, 0, 0) - prices[i]}
         *
         * Base Case:
         *      - f(-1, 1, 0) = 0
         *      - f(-1, 1, 1) = -Inf
         *      - f(i, 0, 0) = 0
         */

        //final int NEG_INF = (int) -1e9;
        final int NEG_INF = Integer.MIN_VALUE;
        int k_1_pos_0 = 0 , k_1_pos_1 = NEG_INF;
        for(int i=0;i<prices.length;i++){
            k_1_pos_0 = Math.max(k_1_pos_0, k_1_pos_1 + prices[i]);
            k_1_pos_1 = Math.max(k_1_pos_1, -prices[i]);
        }

        return k_1_pos_0;
    }
}
