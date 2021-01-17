package com.mine;

import com.mine.math.MyPow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMyPow {
    private static MyPow sol = new MyPow();
    @Test
    public void testCase1(){
        assertEquals(0, sol.play(2,-2147483648), 0.000000000001);
    }
}
