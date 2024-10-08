package com.mine.slidingwindow.longestsubstr.atmostone;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithoutRepeatingChar_slidingwindow_chararr {
    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * Example 4:
     *
     * Input: s = ""
     * Output: 0
     *
     *
     * Constraints:
     *
     * 0 <= s.length <= 5 * 104
     * s consists of English letters, digits, symbols and spaces.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128]; //letters, digits, symbols and spaces
        int max=0;
        for(int i=0,j=0; i<s.length();i++){
            while(chars[s.charAt(i)]>0){
                chars[s.charAt(j)]--;
                j++;
            }

            chars[s.charAt(i)]++;
            max = Math.max(max, i-j+1);
        }

        return max;
    }
}
