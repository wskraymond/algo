package com.mine.slidingwindow.longestsubstr.atmostk;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LongestSubstrWithAtMostKDistinct_hashmap {
    /**
     * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "eceba", k = 2
     * Output: 3
     * Explanation: The substring is "ece" with length 3.
     * Example 2:
     *
     * Input: s = "aa", k = 1
     * Output: 2
     * Explanation: The substring is "aa" with length 2.
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 104
     * 0 <= k <= 50
     *
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        /**
         * avoid rehash & resize
         * 			- initial capacity
         * 				- default = 2^4 (two to the fourth power)= 16
         * 			- max <= capacity * loadFactor (threshold)
         * 				- or capacity = max / loadFactor
         */
        Map<Character, Integer> chars = new HashMap<>(k+1);
        int max = 0;

        for(int i=0, j=0;i<s.length();i++){
            char c = s.charAt(i);
            chars.put(c, i);

            if(chars.size() > k){
                int leftMost = Collections.min(chars.values());
                j = leftMost + 1;
                chars.remove(s.charAt(leftMost));
            }

            max = Math.max(max , i-j+1);
        }

        return max;
    }
}
