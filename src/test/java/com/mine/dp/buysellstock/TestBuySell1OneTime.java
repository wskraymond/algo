package com.mine.dp.buysellstock;

import com.mine.dp.buysellstock.BuySellOneTime_intuitive;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBuySell1OneTime {
    private static BuySellOneTime_intuitive sol = new BuySellOneTime_intuitive();
    private static BuySellOneTime_dp sol2 = new BuySellOneTime_dp();

    @Test
    public void testCase1(){
        assertEquals(5, sol.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(0, sol.maxProfit(new int[]{7,6,4,3,1}));
        assertEquals(0, sol.maxProfit(new int[]{7}));

        assertEquals(5, sol2.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(0, sol2.maxProfit(new int[]{7,6,4,3,1}));
        assertEquals(0, sol2.maxProfit(new int[]{7}));
    }
}
