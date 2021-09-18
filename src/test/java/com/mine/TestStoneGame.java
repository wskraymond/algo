package com.mine;

import com.mine.gametheory.StoneGame3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStoneGame {
    private static StoneGame3 sol3 = new StoneGame3();

    @Test
    public void testCase1(){
        assertEquals(true, sol3.play(new int[]{4,5}));
        assertEquals(true, sol3.play(new int[]{1,2,3,4}));
        assertEquals(true, sol3.play(new int[]{7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24}));
    }
}
