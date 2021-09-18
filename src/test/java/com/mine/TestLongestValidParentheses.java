package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.jpm.parenthesis.LongestValidParentheses;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestValidParentheses {
    private static LongestValidParentheses sol = new LongestValidParentheses();
    @Test
    public void testCase1(){
        assertEquals(2, sol.longestValidParentheses("()(()"));
    }
}
