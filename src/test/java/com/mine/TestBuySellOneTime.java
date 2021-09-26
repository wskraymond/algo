package com.mine;

import com.mine.jpm.buysellstock.BuySellOneTime_slidewindow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBuySellOneTime {
    private static BuySellOneTime_slidewindow sol = new BuySellOneTime_slidewindow();
    @Test
    public void testCase1(){
        assertEquals(5, sol.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(0, sol.maxProfit(new int[]{7,6,4,3,1}));
    }
}
