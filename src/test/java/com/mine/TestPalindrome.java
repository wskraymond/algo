package com.mine;

import com.mine.dp.palindrome.longestsubstr.Palindrome3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPalindrome {
    private static Palindrome3 sol1 = new Palindrome3();

    @Test
    public void testCase1(){
        assertEquals("", sol1.longestPalindrome(""));
        assertEquals("a", sol1.longestPalindrome("a"));
        assertEquals("b", sol1.longestPalindrome("ba"));
        assertEquals("bab", sol1.longestPalindrome("bab"));
        assertEquals("bab", sol1.longestPalindrome("baba"));
        assertEquals("babab", sol1.longestPalindrome("babab"));
        assertEquals("bbbbbbbbbbbb", sol1.longestPalindrome("qwebababrtybbbbbbbbbbbbll"));
    }
}
