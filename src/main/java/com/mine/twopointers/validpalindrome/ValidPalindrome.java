package com.mine.twopointers.validpalindrome;

public class ValidPalindrome {
    /**
     * https://leetcode.com/problems/valid-palindrome/
     * Given a string s, determine if it is a palindrome,
     * 1) considering only alphanumeric characters
     * 2) and ignoring cases.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     * Example 2:
     *
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 2 * 105
     * s consists only of printable ASCII characters.
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
//        boolean result = true;
        for(int i=0, j=s.length()-1; i<j; i++, j--){
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }

            while(i<j && !Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }

            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
//                result = false;
                return false; // break the loop immediately
            }
        }

        return true;
    }
}
