package com.mine.stack.parenthesis;

//Hard

import java.util.ArrayDeque;
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
public class LongestValidParentheses {
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
         * likely to be faster than {@link Stack} when used as a stack
         * And faster than {@link LinkedList} when used as a queue.
         */
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        stack.push(-1);

        int max = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            } else if(s.charAt(i)==')'){
                //call pop() no matter what
                int top = stack.pop();
                /**
                 * another version
                 *
                 * in case that current elment is close,
                 * we don't need to check if the parentheses poped is open or not,
                 * Becos if it is not empty, then it must be open
                 *
                 * for example of "))(" , the stack will only contain 1 element which is close or -1
                 *     - 0: -1 -> 0 //top=0
                 *     - 1: 0 -> 1  //top=1
                 * only when the next one is open , then stack size is larger than 2,
                 *     - 2: 1, 2
                 *
                 * stack.pop();
                 * if (stack.empty()) {
                 *     stack.push(i);
                 * } else {
                 *     maxans = Math.max(maxans, i - stack.peek());
                 * }
                 */
                if(top>-1 && s.charAt(top)=='('){
                    //if close matches open
                    //length = current index - topmost after pop
                    max = Math.max(max, i - stack.peek());
                } else if(stack.isEmpty()) {
                    //if stack is empty after pop
                    //push current index to set the separate line (break point)
                    stack.push(i);
                } // if not empty , then do nothing
            } else {
                throw new IllegalArgumentException();
            }
        }

        return max;
    }
}
