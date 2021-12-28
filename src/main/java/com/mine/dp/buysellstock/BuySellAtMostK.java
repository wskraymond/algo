package com.mine.dp.buysellstock;

public class BuySellAtMostK {
    /**
     * You are given an integer array prices where prices[i] is
     * the price of a given stock on the ith day, and an integer k.
     *
     * Find the maximum profit you can achieve.
     * You may complete at most k transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously
     * (i.e., you must sell the stock before you buy again).
     *
     *
     *
     * Example 1:
     *
     * Input: k = 2, prices = [2,4,1]
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: k = 2, prices = [3,2,6,5,0,3]
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *
     *
     * Constraints:
     *
     * 0 <= k <= 100
     * 0 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     *
     *
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        /**
         * To provide enough info to the subproblem:
         *      1. (Optimal value)Net Profit =  buy(-) + sell(+)
         *      2. Constraint: At most K transaction in n days
         *
         * Subproblem: 3 states
         *      1. prefix[:i] on ith day , i + 1 days have been gone through
         *      2. At most j transactions are concluded in this subproblem
         *      3. pos: position {0, 1}
         *
         * Guess: action to take from i-1th day to ith day
         *      1. hold
         *      2. buy/sell for cash in/out
         *      3. transaction increment when buy a stock
         *
         * Recurrence Relations:
         *      1. Pos = 0
         *         f(i,j,0) = max{f(i-1, j, 0), f(i-1, j , 1) + prices[i] }
         *
         *      2. Pos = 1
         *         f(i,j,1) = max{f(i-1, j, 1), f(i-1,j-1,0) - prices[i]}
         *
         * Base case: (or some boundary case)
         *      1. Pos = 0
         *          f(i,0,0) = f(-1, j, 0) = 0
         *      2. Pos = 1
         *          f(i,0,1) = f(-1, j, 1) = -Inf
         *
         *
         * Goal: Max{f(n-1,j,0)}
         */

        final int n = prices.length;
        final int DAYOFFSET = 1;
        //0 <= prices[i] <= 1000 And 0 <= k <= 100
        //Alternatively, we can set to -1001
        //Or , becos only addition ,  dp[i+DAYOFFSET-1][j][1] + prices[i], we can use Integer.MIN_VALUE
        final int NEG_INF = (int) -1e9;
        int[][][] dp = new int[n+1][k+1][2]; //n+1 includes -1,0, 1....n-1
        for(int j=0;j<=k;j++){
            dp[-1+DAYOFFSET][j][0] = 0;
            dp[-1+DAYOFFSET][j][1] = NEG_INF;
        }

        for(int i=-1;i<n;i++){
            dp[i+DAYOFFSET][0][0] = 0;
            dp[i+DAYOFFSET][0][1] = NEG_INF;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<=k;j++){
                //Pos = 0: f(i,j,0) = max{ f(i-1, j, 0), f(i-1, j , 1) - prices[i] }
                //positive: cash inflow(sold)
                dp[i+DAYOFFSET][j][0] = Math.max(dp[i+DAYOFFSET-1][j][0], dp[i+DAYOFFSET-1][j][1] + prices[i]);

                //Pos = 1: f(i,j,1) = max{ f(i-1, j, 1), f(i-1,j-1,0) + prices[i]}
                if(j>0){
                    // f(i,0,1) = -Inf
                    dp[i+DAYOFFSET][j][1] = Math.max(dp[i+DAYOFFSET-1][j][1], dp[i+DAYOFFSET-1][j-1][0] - prices[i]);
                }
            }
        }

        /**
         * leetcode has redundant or misleading part below
         *
         * At most k has already concludes the optimal/the best path of all possible transactions from 0 to k.
         */
        /*int max = Integer.MIN_VALUE;
        for(int j=0;j<=k;j++){
            max = Math.max(max, dp[n+DAYOFFSET-1][j][0]);
        }*/

        return dp[n+DAYOFFSET-1][k][0];
    }
}
