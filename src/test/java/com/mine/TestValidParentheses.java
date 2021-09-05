package com.mine;

import com.mine.jpm.parenthesis.ValidParentheses;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestValidParentheses {
    private static ValidParentheses sol = new ValidParentheses();
    @Test
    public void testCase1(){
        assertEquals(true, sol.isValid("{}"));
        assertEquals(true, sol.isValid("[]"));
        assertEquals(true, sol.isValid("()"));

        assertEquals(true, sol.isValid("[(()[]){()}]"));
        assertEquals(true, sol.isValid("{[]}"));

        assertEquals(false, sol.isValid("{{"));
        assertEquals(false, sol.isValid("[["));
        assertEquals(false, sol.isValid("(("));

        assertEquals(false, sol.isValid("}}"));
        assertEquals(false, sol.isValid("]]"));
        assertEquals(false, sol.isValid("))"));

        assertEquals(false, sol.isValid("([)]"));
    }
}
