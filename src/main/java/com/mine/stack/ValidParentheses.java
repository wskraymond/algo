package com.mine.stack;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParentheses {
    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     *     Open brackets must be closed by the same type of brackets.
     *     Open brackets must be closed in the correct order.
     *
     *
     * Example 1:
     *
     * Input: s = "()"
     * Output: true
     *
     * Example 2:
     *
     * Input: s = "()[]{}"
     * Output: true
     *
     * Example 3:
     *
     * Input: s = "(]"
     * Output: false
     *
     * Example 4:
     *
     * Input: s = "([)]"
     * Output: false
     *
     * Example 5:
     *
     * Input: s = "{[]}"
     * Output: true
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        //Stack<Character> is synchronized version
        Deque<Character> stack = new LinkedList<>();
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            Character head;
            switch(c){
                case '{': case '[': case '(':
                    stack.push(c);
                    break;
                case '}':
                    head = stack.peek();
                    if(head==null || head.charValue()!='{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    head = stack.peek();
                    if(head==null || head.charValue()!='[') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ')':
                    head = stack.peek();
                    if(head==null || head.charValue()!='(') {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return stack.isEmpty();
    }
}
