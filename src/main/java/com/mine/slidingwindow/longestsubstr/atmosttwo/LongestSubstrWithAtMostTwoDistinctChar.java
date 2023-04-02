package com.mine.slidingwindow.longestsubstr.atmosttwo;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithAtMostTwoDistinctChar {
    /**
     * Given a string s,
     * return the length of the longest substring
     * that contains at most two distinct characters.
     *
     * Example 1:
     *
     * Input: s = "eceba"
     * Output: 3
     * Explanation: The substring is "ece" which its length is 3.
     *
     * Example 2:
     *
     * Input: s = "ccaabbb"
     * Output: 5
     * Explanation: The substring is "aabbb" which its length is 5.
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length <= 105
     *     s consists of English letters.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> chars = new HashMap<>();
        int max = 0;
        for(int i=0, j=0;i<s.length();i++){
            char r = s.charAt(i);
            if(chars.containsKey(r)){
                chars.put(r, chars.get(r) + 1);
            } else{
                chars.put(r, 1);
            }

            while(chars.size() > 2){
                char l = s.charAt(j);
                if(chars.get(l) == 1){
                    chars.remove(l);
                } else {
                    chars.put(l, chars.get(l) - 1);
                }
                j++;
            }

            max = Math.max(max, i-j+1);
        }

        return max;
    }
}
