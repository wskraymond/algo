package com.mine.greedy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCoinChangeInSpecialSetup {
    private static CoinChangeInSpecialSetup sol = new CoinChangeInSpecialSetup();

    //infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes

    @Test
    public void testNormalInput(){
        //2837
        //1000 x 2 + 500 + 100 x 3 + 20 + 10 + 5 + 2
        //output = 10
        assertEquals(10, sol.coinChange(2837));
    }

    @Test
    public void testAmount1000(){
        //1000
        //instead of using small coins/notes
        //1000<-500 x 2 <- (100x 5) x 2 <- ((50x2)x5) x 2 <- (((20x2+10)x2)x5) x 2 <- ...
        //Proof: except the max,  a larger coin is divisible by at least one of smaller coin (L%S=0)
        //By Aggregation, fewer coins/notes are required to change.
        assertEquals(1, sol.coinChange(1000));
    }

    @Test
    public void testZero(){
        assertEquals(0, sol.coinChange(0));
    }
}
