package com.mine;

import com.mine.slidingwindow.longestsubstr.LongestSubstrWithoutRepeatingChar_slidingwindow_chararr;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLongestSubStrWithoutRepeatingChar {
    private static LongestSubstrWithoutRepeatingChar_slidingwindow_chararr sol = new LongestSubstrWithoutRepeatingChar_slidingwindow_chararr();
    @Test
    public void testCase1(){
        assertEquals(1, sol.lengthOfLongestSubstring("a"));
        assertEquals(1, sol.lengthOfLongestSubstring("aaaa"));
        assertEquals(6, sol.lengthOfLongestSubstring("abcdefa"));
        assertEquals(3, sol.lengthOfLongestSubstring("abcabcbb"));
    }
}
