package com.mine.slidingwindow.targetsubstr.anagram;

import java.util.Arrays;

public class ValidAnagram_counter {
    /**
     *
     * https://leetcode.com/problems/valid-anagram/
     *
     * Given two strings s and t,
     * return true if t is an anagram of s,
     * and false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     *
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     * Output: false
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length, t.length <= 5 * 104
     *     s and t consist of lowercase English letters.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        int[] chars = new int[26]; //lowercase letters
        for(int i=0;i<s.length();i++){
            chars[s.charAt(i) - 'a']++;
            chars[t.charAt(i) - 'a']--;
        }

        for(int count: chars){
            if(count>0){
                return false;
            }
        }

        return true;
    }
}
