package com.mine.slidingwindow.targetsubstr;

public class PermutationInString {
    /**
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
        int[] chars = new int[26]; //lowercase English letters
        int count = s1.length();

        //init
        for(int i=0; i<s1.length(); i++){
            chars[s1.charAt(i) - 'a']++;
        }

        for(int i=0, j=1; i<s2.length() && count>0 ; i++){
            char r = s2.charAt(i);
            if(chars[r - 'a']>0){
                chars[r-'a']--;
                count--;
            } else {
                if(j==i){
                    j++;
                }
                while(j<i){
                    char l = s2.charAt(j);
                    chars[l - 'a']++;
                    j++;
                    count++;
                }
            }
        }

        return count==0;
    }
}
