package com.mine;

import com.mine.ms.LongestUniformSubstring;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestUniformSubstring {
    private static LongestUniformSubstring sol = new LongestUniformSubstring();
    /**
     * case 0
     * 0123456
     * aaaaaac
     *
     * case 1
     * 01234567
     * abbbccda
     * bbb => [1,3]
     *
     * case 2
     * 01234567
     * accccccc
     */

    @Test
    public void testCase1(){
        assertEquals("a", sol.play("a"));
        assertEquals("aaaaaa", sol.play("aaaaaac"));
        assertEquals("bbb", sol.play("abbbccda"));
        assertEquals("ccccccc", sol.play("accccccc"));
    }
}
