package com.mine;

import com.mine.dp.DecodeWay;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDecodeWay {
    private static DecodeWay sol = new DecodeWay();
    @Test
    public void testCase1(){
        assertEquals(1, sol.numDecodings("44"));
        assertEquals(3, sol.numDecodings("226"));
    }
}
