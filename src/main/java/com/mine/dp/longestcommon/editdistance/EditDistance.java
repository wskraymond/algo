package com.mine.dp.longestcommon.editdistance;

import java.util.Arrays;

public class EditDistance {
    /**
     * https://leetcode.com/problems/edit-distance/
     *
     * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
     *
     * You have the following three operations permitted on a word:
     *
     * Insert a character
     * Delete a character
     * Replace a character
     *
     *
     * Example 1:
     *
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * Example 2:
     *
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        //convert x -> y
        int X= word1.length() , Y = word2.length();
        int[][] dp = new int[X+1][Y+1];
        for(int[] arr:dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        dp[X][Y]=0;
        for(int i=X;i>=0;i--){
            for(int j=Y;j>=0;j--){
                //insert
                if(j+1<=Y) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }

                //delete
                if(i+1<=X){
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j] + 1);
                }

                //replace or equal
                if(i+1<=X && j+1<=Y){
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j+1] + (word1.charAt(i)==word2.charAt(j) ? 0 : 1));
                }

                //for dp[X][Y], it won't edit by either of above 3 operation, and it is the base case
            }
        }

        return dp[0][0];
    }
}
