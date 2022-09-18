package com.mine.dp.palindrome.subsequence;

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
            Sub-problem:
                substring[i:j]

            Recurrence Relation:
                f(i,j) = max{ f(i+1,j),
                            f(i,j-1) ,
                            f(i+1,j-1) + (s[i]==s[j] ? 2 : 0)
                            | if i<=j }
            Base case:
                f(i,j=i) = 1
            Goal:
                f(0, n-1)

            Take-away:
            Could we apply the above recurrence relation to question - longest palindrome substring ?
                - let's say if we're gonna return the value of the longest length of palindrome
                    - instead of substring as return type
                - Why can't we use Math.max to resolve it ?
                    - No. becos the integer value of sub-problem cannot tell if is palindrome
                        - The main difference why above function works is
                            - subsequence(0/1 in order) vs substring(contiguous)

         */
        int n = s.length();
        if(n==1){
            return 1;
        }

        int[][] dp = new int[n][n];
        IntStream.range(0,n).forEach(i->dp[i][i]=1);
        for(int size=2;size<=n;size++){
            for(int i=0;i<=n-size;i++){
                int j = i+size-1;
                if(i+1<=j){
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                }

                if(i<=j-1){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
                }

                dp[i][j] = Math.max(dp[i][j],
                        (i+1<=j-1 ? dp[i+1][j-1] : 0)
                                + (s.charAt(i)==s.charAt(j) ? 2:0));
            }
        }

        return dp[0][n-1];
    }
}
