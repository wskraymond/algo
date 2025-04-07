package com.practice;

import java.util.stream.IntStream;

public class LongestPalindromeSubseq {
    /**
     * Given a string s, find the longest palindromic subsequence's length in s.
     *
     * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "bbbab"
     * Output: 4
     * Explanation: One possible longest palindromic subsequence is "bbbb".
     *
     * Example 2:
     *
     * Input: s = "cbbd"
     * Output: 2
     * Explanation: One possible longest palindromic subsequence is "bb".
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length <= 1000
     *     s consists only of lowercase English letters.
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        /*
            Take-away:
            Could we apply the above recurrence relation to question - longest palindrome substring ?
                - let's say if we're gonna return the value of the longest length of palindrome
                    - instead of substring as return type
                - Why can't we use Math.max to resolve it ?
                    - No. becos the integer value of sub-problem cannot tell if is palindrome
                        - The main difference why above function works is
                            - subsequence(0/1 in order) vs substring(contiguous)

         */
        /*
            sub-problem: substring[i:j]
            recurrence relation:
            f(i,j) = Max{
                    f(i+1,j-1) + 2 if s[i]==s[j],
                    f(i+1, j),
                    f(i,j-1)
                }
            base:
                f(i,j=i)=1
            goal:
                f(0,n-1)
            Top Order:
                for s=2....size
                    for i=0...n-size
                        j=i+size-1
         */
        final int n = s.length();
        int[][] dp = new int[n][n];
        IntStream.range(0,n).forEach(i->dp[i][i]=1);
        for(int size=2;size<=n;size++){
            for(int i=0;i<=n-size;i++){
                int j=i+size-1;
                dp[i][j] = IntStream.of(
                        (i+1<=j-1 ? dp[i+1][j-1] : 0 ) + (s.charAt(i)==s.charAt(j) ? 2 : 0),
                        dp[i+1][j],
                        dp[i][j-1]
                        ).max().getAsInt();
            }
        }

        return dp[0][n-1];
    }
}
