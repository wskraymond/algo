package com.mine.dp.longestcommon.common;

public class LongestCommonSubsequence_2d {
    /**
     * Given two strings text1 and text2,
     * return the length of their longest common subsequence.
     * If there is no common subsequence, return 0.
     *
     * A subsequence of a string is
     * a new string generated from the original string
     * with some characters (can be none) deleted
     * without changing the relative order of the remaining characters.
     *
     * For example, "ace" is a subsequence of "abcde".
     * A common subsequence of two strings is a subsequence that is common to both strings.
     *
     *
     *
     * Example 1:
     *
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3
     * Explanation: The longest common subsequence is "ace" and its length is 3.
     * Example 2:
     *
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3
     * Explanation: The longest common subsequence is "abc" and its length is 3.
     * Example 3:
     *
     * Input: text1 = "abc", text2 = "def"
     * Output: 0
     * Explanation: There is no such common subsequence, so the result is 0.
     *
     *
     * Constraints:
     *
     * 1 <= text1.length, text2.length <= 1000
     * text1 and text2 consist of only lowercase English characters.
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * x = text1
         * y = text2
         *
         * Recurrence Relation:
         *  - f(i,j) = max{ f(i+1, j) for move forward i, f(i,j+1) for move forward j, f(i+1,j+1) + equal(i,j) }
         *
         * Base case:
         *  - f(|x|, j) = 0
         *  - f(i, |y|) = 0
         *
         * Goal:
         *  - f(0,0) for x[0:] and y[0:]
         *
         */

        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i=text1.length()-1;i>=0;i--){
            for(int j=text2.length()-1;j>=0;j--){
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j+1]);
                /**
                 //wrong code
                 dp[i][j] = Math.max(dp[i][j], dp[i+1][j+1] + text1.charAt(i)==text2.charAt(j) ? 1 : 0);
                 beware of taking the parenthesis of the conditional operator
                 * */

                dp[i][j] = Math.max(dp[i][j], dp[i+1][j+1] + (text1.charAt(i)==text2.charAt(j) ? 1 : 0));
            }
        }

        return dp[0][0];
    }
}
