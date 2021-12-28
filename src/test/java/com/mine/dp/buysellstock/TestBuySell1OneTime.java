package com.mine.dp.buysellstock;

import com.mine.dp.buysellstock.BuySellOneTime_intuitive;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testPositiveOverflow(){
        assertEquals(Integer.MAX_VALUE, sol.maxProfit(new int[]{0,Integer.MAX_VALUE}));
        assertEquals(Integer.MAX_VALUE, sol2.maxProfit(new int[]{0,Integer.MAX_VALUE}));
    }

    @Test
    public void testNegativeOverflow(){
        //beware of Integer.MAX_VALUE= 2^31 - 1 < 2^31 =Integer.MIN_VALUE
        //-MAX -> ok
        assertEquals(-2147483647, -Integer.MAX_VALUE);
        //-MIN -> fail
        assertNotEquals(2147483648l, -Integer.MIN_VALUE);
        assertEquals(0, sol.maxProfit(new int[]{Integer.MAX_VALUE,(int)1e9}));
        assertEquals(0, sol2.maxProfit(new int[]{Integer.MAX_VALUE,(int)1e9}));
    }
}
