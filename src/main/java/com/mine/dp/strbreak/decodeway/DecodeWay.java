package com.mine.dp.strbreak.decodeway;

public class DecodeWay {
    /**
     * A message containing letters from A-Z can be encoded
     *  into numbers using the following mapping:
     *
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     *
     * To decode an encoded message, all the digits must be grouped
     * then mapped back into letters using the reverse of the mapping above
     * (there may be multiple ways). For example, "11106" can be mapped into:
     *
     *     "AAJF" with the grouping (1 1 10 6)
     *     "KJF" with the grouping (11 10 6)
     *
     * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
     *
     * Given a string s containing only digits, return the number of ways to decode it.
     *
     * Example 1:
     *
     * Input: s = "12"
     * Output: 2
     * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
     *
     * Example 2:
     *
     * Input: s = "226"
     * Output: 3
     * Explanation: "226" could be decoded as "BZ" (2 26),
     *              "VF" (22 6),
     *              or "BBF" (2 2 6).
     *
     * Example 3:
     *  input: s = "1226"
     *  Output: 1 226, 12 26, 1 22 6, 1 2 2 6
     *
     * Example 4:
     *   Input: s = "101"
     *   Output: 10 1 (ok)  //excludes 1 01, 1 0 1
     *
     * Example 5:
     *   Input: s = "100"
     *   Output: zero result //excludes 10 0, 1 00 , 1 0 0
     *
     * Example 6:
     *   Input: s = "291"
     *   Output: 2 9 1 //excludes 29 1, 2 91
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        /*
            subproblem: suffix[i:]
                return the number of ways to decode
            recurrence relations:
                f(i) = (isValid(s[i:i+1]) ? 1:0)*f(i+1) + (isValid(s[i:i+2]) ? 1:0)*f(i+2)
            Base case:
                f(n) = 1
            Goal:
                f(0)
         */

        final int n = s.length();
        int[] dp = new int[n+1];
        dp[n]=1;
        for(int i=n-1;i>=0;i--){ //O(n)
            if(i+1<=n) {
                dp[i] += (isValid(s.substring(i, i + 1)) ? 1 : 0) * dp[i + 1];
            }

            if(i+2<=n) {
                dp[i] += (isValid(s.substring(i, i + 2)) ? 1 : 0) * dp[i + 2];
            }
        }

        return dp[0]; //O(n)
    }

    private boolean isValid(String c){ //O(1)
        /*
        contains only digits and may contain leading zero(s).
         */
        /*
            Be careful: "26".compareTo(c) Compares two strings lexicographically
            "0", ..., "1", "10", "11", ......"19","2","20","21", ....."26", "27"...,"29", "3"...."4"....."9"
         */
        //instead , compare char by char
        return c!=null
                && c.length()!=0
                && c.charAt(0)!='0'
                && (c.length()==1 || c.charAt(0)=='1' || c.charAt(0)=='2' && c.charAt(1)<'7');

    }
}
