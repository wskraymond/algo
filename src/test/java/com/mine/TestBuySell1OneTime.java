package com.mine;

import com.mine.jpm.buysellstock.BuySell1OneTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBuySell1OneTime {
    private static BuySell1OneTime sol = new BuySell1OneTime();
    @Test
    public void testCase1(){
        assertEquals(5, sol.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(0, sol.maxProfit(new int[]{7,6,4,3,1}));
    }
}
