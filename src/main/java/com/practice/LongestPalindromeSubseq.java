package com.practice;

import java.util.stream.IntStream;

public class LongestPalindromeSubseq {
    /**
     * Given a string s, find the longest palindromic subsequence's length in s.
     *
     * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "bbbab"
     * Output: 4
     * Explanation: One possible longest palindromic subsequence is "bbbb".
     *
     * Example 2:
     *
     * Input: s = "cbbd"
     * Output: 2
     * Explanation: One possible longest palindromic subsequence is "bb".
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length <= 1000
     *     s consists only of lowercase English letters.
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        /*
            Take-away:
            Could we apply the above recurrence relation to question - longest palindrome substring ?
                - let's say if we're gonna return the value of the longest length of palindrome
                    - instead of substring as return type
                - Why can't we use Math.max to resolve it ?
                    - No. becos the integer value of sub-problem cannot tell if is palindrome
                        - The main difference why above function works is
                            - subsequence(0/1 in order) vs substring(contiguous)

         */
        return 0;
    }
}
