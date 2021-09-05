package com.mine.jpm;

import java.util.HashMap;
import java.util.Map;

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

    private Map<Integer, Integer> m = new HashMap<>();

    public int numDecodings(String s) {
        if(m.containsKey(s.length())){
            return m.get(s.length());
        }

        if(s==null || s.isEmpty()){
            return 0;
        }

        if(s.charAt(0)=='0'){
            return 0;
        }

        if(s.length()==1){
            return 1;
        }

        int result = numDecodings(s.substring(1));
        String c = s.substring(0, 2);
        if(c.compareTo("26") <= 0){
            result += s.length() > 2 ? numDecodings(s.substring(2)): 1;
        }

        m.put(s.length(), result);
        return result;
    }
}
