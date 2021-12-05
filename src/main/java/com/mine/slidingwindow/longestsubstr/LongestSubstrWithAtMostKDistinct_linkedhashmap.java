package com.mine.slidingwindow.longestsubstr;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LongestSubstrWithAtMostKDistinct_linkedhashmap {
    /**
     * Given a string s and an integer k,
     * return the length of the longest substring
     * of s that contains at most k distinct characters.
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
        if(s.length() <= k){
            return s.length();
        }

        /**
         * - avoid collision/linear search
         * 			- loadFactor
         * 				- default: 0.75
         * 				- tradeoff between space and time complexity
         * 			- power of 2
         * 	- avoid rehash & resize
         * 			- initial capacity
         * 				- default = 2^4 (two to the fourth power)= 16
         * 			- max <= capacity * loadFactor (threshold)
         * 				- or capacity = max / loadFactor
         */
        Map<Character, Integer> chars = new LinkedHashMap<>( (k+1) * 4/3 + 1);
        int max = 0;

        for(int i=0, j=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(chars.containsKey(c)){
                chars.remove(c);
            }
            chars.put(c, i);

            if(chars.size() > k){
//                int leftMost = Collections.min(chars.values());
                int leftMost = chars.values().iterator().next();
                j = leftMost + 1;
                chars.remove(s.charAt(leftMost));
            }

            max = Math.max(max , i-j+1);
        }

        return max;
    }
}
