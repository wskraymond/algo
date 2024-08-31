package com.mine;

import com.mine.dp.strbreak.decodeway.DecodeWay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDecodeWay {
    private static DecodeWay sol = new DecodeWay();
    @Test
    public void testCase1(){
        assertEquals(0, sol.numDecodings("'000'"));
        assertEquals(0, sol.numDecodings("'1000'"));
        assertEquals(0, sol.numDecodings("'09'"));
        assertEquals(0, sol.numDecodings("'90'"));
        assertEquals(2, sol.numDecodings("'11106'"));
        assertEquals(1, sol.numDecodings("44"));
        assertEquals(3, sol.numDecodings("226"));
    }
}
