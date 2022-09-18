package com.mine.dp.longestcommon;

import java.util.stream.IntStream;

public class DistinctSubsequences {
    /**
     * Given two strings s and t, return the number of distinct subsequences of s which equals t.
     *
     * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     *
     * The test cases are generated so that the answer fits on a 32-bit signed integer.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "rabbbit", t = "rabbit"
     * Output: 3
     * Explanation:
     * As shown below, there are 3 ways you can generate "rabbit" from S.
     * rabbbit
     * rabbbit
     * rabbbit
     *
     * Example 2:
     *
     * Input: s = "babgbag", t = "bag"
     * Output: 5
     * Explanation:
     * As shown below, there are 5 ways you can generate "bag" from S.
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length, t.length <= 1000
     *     s and t consist of English letters.
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];
        IntStream.range(0, n+1).forEach(i->dp[i][m]=1);
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                dp[i][j] = dp[i+1][j] + (s.charAt(i)==t.charAt(j) ? dp[i+1][j+1] : 0);
            }
        }

        return dp[0][0];
    }
}
