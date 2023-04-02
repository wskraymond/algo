package com.mine.stack.parenthesis;

//Hard

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 */
public class LongestValidParentheses02 {
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()".
     *
     * Example 2:
     *
     * Input: s = ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()".
     *
     * Example 3:
     *
     * Input: s = ""
     * Output: 0
     *
     *
     *
     * Constraints:
     *
     *     0 <= s.length <= 3 * 104
     *     s[i] is '(', or ')'.
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        /**
         * case 1: ...() = dp[i-2] + 2
         * case 2: ...(...)) = str[i-dp[i-1]-1]=='(' ? dp[i-1] + 2 + dp[i - dp[i-1] - 1 - 1] : 0;
         * case 3: ...)...))
         *
         * dp[i] = L(i) =longest valid length ends at ith element
         *
         * base case with index boundary checking
         * (i>=k ? L(i-k) : 0)
         *
         * memo:
         *  - new int[n]
         *  - init: dp = 0
         *
         * return max{dp[i]}
         */

        int[] dp = new int[s.length() + 1];
        int max = 0;
        for(int i=1;i<s.length();i++){ //start from 1
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i] = (i-2>=0 ? dp[i-2] : 0) + 2;
                } else if(s.charAt(i-1)==')') {
                    if (i - dp[i - 1] - 1 >= 0
                            && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >=0 ? dp[i - dp[i - 1] - 2] : 0);
                    } //else base case: end of recursion
                } else {
                    throw new IllegalArgumentException();
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
