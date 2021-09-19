package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.jpm.parenthesis.LongestValidParentheses;
import com.mine.jpm.parenthesis.LongestValidParentheses02;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestValidParentheses {
    private static LongestValidParentheses sol = new LongestValidParentheses();
    private static LongestValidParentheses02 sol2 = new LongestValidParentheses02();

    @Test
    public void testCase1(){
        assertEquals(0, sol.longestValidParentheses(""));
        assertEquals(0, sol.longestValidParentheses("("));
        assertEquals(0, sol.longestValidParentheses(")"));
        assertEquals(0, sol.longestValidParentheses("))(("));
        assertEquals(2, sol.longestValidParentheses("()(()"));
        assertEquals(2, sol.longestValidParentheses("())"));
        assertEquals(4, sol.longestValidParentheses(")()())"));
    }

    @Test
    public void testCase2(){
        assertEquals(0, sol2.longestValidParentheses(""));
        assertEquals(0, sol2.longestValidParentheses("("));
        assertEquals(0, sol2.longestValidParentheses(")"));
        assertEquals(0, sol2.longestValidParentheses("))(("));
        assertEquals(2, sol2.longestValidParentheses("()(()"));
        assertEquals(2, sol2.longestValidParentheses("())"));
        assertEquals(4, sol2.longestValidParentheses(")()())"));
    }
}
