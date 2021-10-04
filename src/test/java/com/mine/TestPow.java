package com.mine;

import com.mine.math.Pow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPow {
    private static Pow sol = new Pow();
    @Test
    public void testCase1(){
        assertEquals(0, sol.play(2,-2147483648), 0.000000000001);
    }
}
