package com.mine.dp.palindrome.counting;

import java.util.stream.IntStream;

public class CountingPalindromicSubstr_inward {
    /**
     * Given a string s, return the number of palindromic substrings in it.
     *
     * A string is a palindrome when it reads the same backward as forward.
     *
     * A substring is a contiguous sequence of characters within the string.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abc"
     * Output: 3
     * Explanation: Three palindromic strings: "a", "b", "c".
     *
     * Example 2:
     *
     * Input: s = "aaa"
     * Output: 6
     * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        int n = s.length();
        int count=n;
        boolean[][] dp = new boolean[n][n];
        IntStream.range(0,n).forEach(i->dp[i][i]=true);
        for(int size=2;size<=n;size++){
            for(int i=0;i<=n-size;i++){
                int j = i + size -1;
                dp[i][j] = s.charAt(i)==s.charAt(j) && (i+1<=j-1 ? dp[i+1][j-1] : true);
                if(dp[i][j]){
                    count++;
                }
            }
        }

        return count;
    }
}
