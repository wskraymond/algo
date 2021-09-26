package com.mine;

import com.mine.slidingwindow.NonRepeatChar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNonRepeatChar {
    private static NonRepeatChar sol1 = new NonRepeatChar();

    @Test
    public void testCase1(){
        assertEquals(Character.valueOf('a'), sol1.play("a"));
        assertEquals(Character.valueOf('a'), sol1.play("ab"));
        assertEquals(null,sol1.play("aa"));
        assertEquals(Character.valueOf('b'), sol1.play("aba"));
        assertEquals(Character.valueOf('b'), sol1.play("abannnca"));
        assertEquals(Character.valueOf('b'), sol1.play("aaab"));
        assertEquals(Character.valueOf('b'), sol1.play("abcdnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnac"));
    }
}
