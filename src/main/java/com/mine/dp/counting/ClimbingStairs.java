package com.mine.dp.counting;

public class ClimbingStairs {
    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     *
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n<2){
            return 1;
        }

        /**
         * Recurrence Relation:
         *    f(k) = f(k-1) + f(k-2)
         *    f(2) = f(1) + f(0)
         *         = 1 + 1
         *         = 2
         *
         * Base Case:
         *   f(0) = 1
         *   f(1) = 1
         */

        //TopSort(i): 0.....n
        //DP: count at k-1 and count at k-2
        int countAtK_1 = 1, countAtK_2=1;
        for(int k=2;k<=n; k++){
            countAtK_1 = countAtK_1 + countAtK_2; //next k-1 result
            countAtK_2 = countAtK_1 - countAtK_2; //next k-2 result
        }

        return countAtK_1;
    }
}
