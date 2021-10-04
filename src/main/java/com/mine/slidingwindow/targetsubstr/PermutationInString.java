package com.mine.slidingwindow.targetsubstr;

import java.util.Arrays;

public class PermutationInString {
    /**
     * https://leetcode.com/problems/permutation-in-string/submissions/
     *
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
     *
     * In other words, return true if one of s1's permutations is the substring of s2.
     *
     *
     *
     * Example 1:
     *
     * Input: s1 = "ab", s2 = "eidbaooo"
     * Output: true
     * Explanation: s2 contains one permutation of s1 ("ba").
     *
     * Example 2:
     *
     * Input: s1 = "ab", s2 = "eidboaoo"
     * Output: false
     *
     *
     *
     * Constraints:
     *
     *     1 <= s1.length, s2.length <= 104
     *     s1 and s2 consist of lowercase English letters.
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        //similar with anagram
        if(s1.length()>s2.length()){
            return false;
        }
        int[] sArr = new int[26];
        int[] pArr = new int[26];

        for(int i=0;i<s1.length();i++){
            pArr[s1.charAt(i) - 'a']++;
        }

        for(int i=0,j=0;i<s2.length();i++){
            sArr[s2.charAt(i)-'a']++;
            if(i-j==s1.length()-1){
                if(Arrays.equals(sArr,pArr)){
                    return true;
                }
                sArr[s2.charAt(j)-'a']--;
                j++;
            }
        }

        return false;
    }
}
