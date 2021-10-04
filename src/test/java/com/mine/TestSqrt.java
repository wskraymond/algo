package com.mine;

import com.mine.binarysearch.Sqrt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSqrt {
    private static Sqrt sol = new Sqrt();
    @Test
    public void testCase1(){
        assertEquals(2, sol.play(4));
        assertEquals(6, sol.play(36));
        assertEquals(10, sol.play(100));
        assertEquals(20, sol.play(400));
        assertEquals(2, sol.play(5));
        assertEquals(2, sol.play(7));
        assertEquals(2, sol.play(8));
        assertEquals(3, sol.play(9));
        //2147395599
        assertEquals(46339, sol.play(2147395599));
    }
}
