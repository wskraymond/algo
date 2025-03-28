package com.practice;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinimumWindowSubstring {
    /**
     * Given two strings s and t of lengths m and n respectively, return the minimum window
     * substring
     * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
     *
     * The testcases will be generated such that the answer is unique.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     *
     * Example 2:
     *
     * Input: s = "a", t = "a"
     * Output: "a"
     * Explanation: The entire string s is the minimum window.
     *
     * Example 3:
     *
     * Input: s = "a", t = "aa"
     * Output: ""
     * Explanation: Both 'a's from t must be included in the window.
     * Since the largest window of s only has one 'a', return empty string.
     *
     *
     *
     * Constraints:
     *
     *     m == s.length
     *     n == t.length
     *     1 <= m, n <= 105
     *     s and t consist of uppercase and lowercase English letters.
     *
     *
     *
     * Follow up: Could you find an algorithm that runs in O(m + n) time?
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        /*
            1. use a map to store the key to remaining number of chars that is not yet satisfied (i.e it can be over-statisfied - negative)
            2. shrink(shift j forward) to approch the miminum of window size till the condition is no longer met
            3. use of a counter to keep track of how many chars in the string 't' we meet
         */

        /*
            time: O(m)
            space: O(m)
         */

        //total time; O(m+n)
        return null;
    }
}
