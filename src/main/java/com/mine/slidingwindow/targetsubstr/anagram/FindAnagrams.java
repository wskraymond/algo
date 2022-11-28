package com.mine.slidingwindow.targetsubstr.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindAnagrams {
    /**
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "cbaebabacd", p = "abc"
     * Output: [0,6]
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     *
     * Example 2:
     *
     * Input: s = "abab", p = "ab"
     * Output: [0,1,2]
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     *
     * Constraints:
     *
     *     1 <= s.length, p.length <= 3 * 104
     *     s and p consist of lowercase English letters.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(p.length()>s.length()){
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<>();
        int[] sArr = new int[26];
        int[] pArr = new int[26];

        for(int i=0;i<p.length();i++){
            pArr[p.charAt(i) - 'a']++;
        }

        /*for(int i=0,j=0;i<s.length();i++){
            sArr[s.charAt(i)-'a']++;
            if(i-j==p.length()-1){
                if(Arrays.equals(sArr,pArr)){
                    result.add(j);
                }
                sArr[s.charAt(j)-'a']--;
                j++;
            }
        }*/

        for(int i=0;i<s.length();i++){
            sArr[s.charAt(i)-'a']++;
            if(i>=p.length()-1){
                int start = i-(p.length()-1);
                if(Arrays.equals(sArr,pArr)){
                    result.add(start);
                }
                sArr[s.charAt(start)-'a']--;
            }
        }

        return result;
    }
}
