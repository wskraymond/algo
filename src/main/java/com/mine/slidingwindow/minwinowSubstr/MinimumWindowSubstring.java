package com.mine.slidingwindow.minwinowSubstr;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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
        Map<Character, Long> m = t.chars()
                                .mapToObj(c->(char)c)
                                .collect(Collectors.groupingBy(Function.identity(),
                                        Collectors.counting()));
        int start=0,end=0;
        int min = Integer.MAX_VALUE, counter = t.length();
        for(int i=0,j=0;i<s.length();i++){
            Long remaining = m.computeIfPresent(s.charAt(i), (k,v)-> --v);
            if(remaining!=null
                && remaining>=0){
                counter--;
            }
            while(counter==0){
                if(i-j+1<min){
                    start = j;
                    end = i+1;
                    min = end -start;
                }

                remaining = m.computeIfPresent(s.charAt(j), (k,v)-> ++v);
                if(remaining!=null
                        && remaining>0){
                    counter++;
                }

                j++;
            }
        }

        return s.substring(start,end);
    }
}
