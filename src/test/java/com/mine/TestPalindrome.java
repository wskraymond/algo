package com.mine;

import com.mine.dp.palindrome.substring.nonbruteforce.old.Palindrome_outward_from_center_old;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPalindrome {
    private static Palindrome_outward_from_center_old sol1 = new Palindrome_outward_from_center_old();

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
