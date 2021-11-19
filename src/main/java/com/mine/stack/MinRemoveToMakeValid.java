package com.mine.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinRemoveToMakeValid {
    /**
     * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
     *
     *
     * Given a string s of '(' , ')' and lowercase English characters.
     *
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
     *
     * Formally, a parentheses string is valid if and only if:
     *
     *     It is the empty string, contains only lowercase characters, or
     *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
     *     It can be written as (A), where A is a valid string.
     *
     * xample 1:
     *
     * Input: s = "lee(t(c)o)de)"
     * Output: "lee(t(c)o)de"
     * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
     *
     * Example 2:
     *
     * Input: s = "a)b(c)d"
     * Output: "ab(c)d"
     *
     * Example 3:
     *
     * Input: s = "))(("
     * Output: ""
     * Explanation: An empty string is also valid.
     *
     * Example 4:
     *
     * Input: s = "(a(b(c)d)"
     * Output: "a(b(c)d)"
     *
     *
     *
     * Constraints:
     *
     *     1 <= s.length <= 10^5
     *     s[i] is one of  '(' , ')' and lowercase English letters.
     *
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        /**
         * only '(' and ')'
         * case 1:
         *      (() or ()( or (
         * case 2:
         *      ()) or )() or )
         * case 3:
         *      )()( or )(() or ())( or )(
         */
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        for(int i=0;i<s.length();i++){ //O(n)
            if(s.charAt(i)=='('){
                stack.push(i);  //O(1)
            } else if(s.charAt(i)==')'){
                if(!stack.isEmpty()) {
                    int top = stack.peek(); //O(1)
                    if (s.charAt(top) == '(') {
                        stack.pop();    //O(1)
                    } else {
                        stack.push(i);
                    }
                } else {
                    stack.push(i);
                }
            } // letters
        }

        Set<Integer> removes = new HashSet<>();
        while(!stack.isEmpty()){ //O(n)
            removes.add(stack.pop());   //O(1)
        }

        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){  //O(n)
            if(!removes.contains(i)) {  //O(1)
                result.append(s.charAt(i)); //O(1)
            }
        }

        return result.toString();   //O(3n)
    }
}
