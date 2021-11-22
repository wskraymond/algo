package com.mine.dp.longestcommon.uncommon;

public class LongestUncommonSeq {
    /**
     * Given two strings a and b,
     *    - return the length of the longest uncommon subsequence between a and b.
     *    - If the longest uncommon subsequence does not exist, return -1.
     *
     * An uncommon subsequence between two strings is
     * a string that is a subsequence of one but not the other.
     *
     * A subsequence of a string s is
     * a string that can be obtained after deleting any number of characters from s.
     *      For example, "abc" is a subsequence of "aebdc"
     *      because you can delete the underlined characters in "aebdc" to get "abc".
     *      Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
     *
     *
     * Example 1:
     *
     * Input: a = "aba", b = "cdc"
     * Output: 3
     *
     * Explanation:
     * One longest uncommon subsequence is "aba"
     * because "aba" is a subsequence of "aba" but not "cdc".
     *
     * Note that "cdc" is also a longest uncommon subsequence.
     *
     * Constraints:
     *
     * 1 <= a.length, b.length <= 100
     * a and b consist of lower-case English letters.
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        boolean[] aChars = new boolean[26];

        for(int i=0;i<a.length();i++){
            aChars[a.charAt(i) - 'a'] = true;
        }

        int aL = a.length();
        int bL = 0;
        for(int i=0;i<b.length();i++){
            if(!aChars[b.charAt(i)-'a']){
                bL++;
            } else {
                aL--;
            }
        }

        int result = Math.max(aL,bL);
        return result!=0 ? result : -1;
    }
}
