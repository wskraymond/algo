package com.mine.twopointers.validpalindrome;

public class ValidPalindromeRemoveOne {
    /**
     Given a string s, return true if the s can be palindrome after deleting at most one character from it.

     Example 1:

     Input: s = "aba"
     Output: true
     Example 2:

     Input: s = "abca"
     Output: true
     Explanation: You could delete the character 'c'.
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int l = -1, r = s.length();
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return isPalindromic(s, l, r+1) || isPalindromic(s, l-1, r);
        return true;
    }

    private boolean isPalindromic(String s, int l, int r) {
        while (++l < --r)
            if (s.charAt(l) != s.charAt(r)) return false;
        return true;
    }
}
