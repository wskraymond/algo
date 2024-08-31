package com.mine.dp.strbreak.wordbreak;

import java.util.List;

public class wordBreak {
    /**
     * Given a string s and a dictionary of strings wordDict,
     * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "leetcode", wordDict = ["leet","code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * Example 2:
     *
     * Input: s = "applepenapple", wordDict = ["apple","pen"]
     * Output: true
     * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     * Note that you are allowed to reuse a dictionary word.
     * Example 3:
     *
     * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 300
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 20
     * s and wordDict[i] consist of only lowercase English letters.
     * All the strings of wordDict are unique.
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
            suffix subproblem:
                    f(i) = Any{ s[i:i+len]==w && f(i+len) for w, len in words }
            base case:
                f(n) = true
            Goal:
                f(o)

         */
        /*
            Bruteforce Time Complexity: O(m^n)
            use dp to reduce to polynomial
         */

        final int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n]=true;
        for(int i=n-1;i>=0;i--){ //O(n)
            for(String w :wordDict){ //O(m)
                if(i+w.length()<=n){
                    /*
                        if we convert wordDict to set(search=O(1)) ,
                        we still need to get the substring of s which also costs O(k) averagely
                        Thus, conversion to HashSet is redundant.
                     */

                    //1 <= wordDict[i].length <= 20
                    //equal() check avg cost: O(k)
                    dp[i] = s.startsWith(w, i) && dp[i+w.length()];
                    if(dp[i]){
                        break;
                    }
                }
            }
        }
        return dp[0]; //O(n*m*k)
    }
}
