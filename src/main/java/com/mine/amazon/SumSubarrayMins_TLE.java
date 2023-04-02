package com.mine.amazon;

public class SumSubarrayMins_TLE {
    /**
     * https://leetcode.com/problems/sum-of-subarray-minimums/
     *
     * Given an array of integers arr,
     * find the sum of min(b),
     * where b ranges over every (contiguous) subarray of arr.
     * Since the answer may be large, return the answer modulo 109 + 7.
     *
     * Example 1:
     *
     * Input: arr = [3,1,2,4]
     * Output: 17
     * Explanation:
     * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
     * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
     * Sum is 17.
     *
     * Example 2:
     *
     * Input: arr = [11,81,94,43,3]
     * Output: 444
     *
     * Constraints:
     *
     *     1 <= arr.length <= 3 * 104
     *     1 <= arr[i] <= 3 * 104
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        /**
         * recurrence relation:
         *      f(i,j) = min{f(i,j-1), arr[i]}
         *
         * base case:
         *      f(i,i) = arr[i]
         *
         * Goal:
         *      sum{f(i,j)}
         */

        /**
         * Optimal solution: Monotonic Stack(Trapping Rain Water)
         */

        //https://www.geeksforgeeks.org/modulo-1097-1000000007/
        //to avoid overflow
        final int M = (int)1e9 + 7;

        int result = 0;
        int[][] dp = new int[arr.length][arr.length];
        for(int size=1;size<=arr.length;size++){
            for(int i=0, j=i+size-1;j<arr.length;j=++i+size-1){
                if(i==j){
                    dp[i][j]=arr[i]%M;
                } else {
                    dp[i][j]= Math.min(dp[i][j-1], arr[j]) %M;
                }

                result+=dp[i][j];
                result%=M;
            }
        }

        return result;
    }
}
