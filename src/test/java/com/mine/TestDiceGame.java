package com.mine;

import com.mine.dp.counting.DPDiceGame_recursion;
import com.mine.dp.counting.DPDiceGame_topsort;
import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDiceGame {
    private static DPDiceGame_recursion sol1 = new DPDiceGame_recursion();
    private static DPDiceGame_topsort sol2 = new DPDiceGame_topsort();
    @Test
    public void testCase1(){
        assertEquals(sol1.countDiceProb(0), sol2.countDice(0));
        assertEquals(sol1.countDiceProb(1), sol2.countDice(1));
        assertEquals(sol1.countDiceProb(2), sol2.countDice(2));
        assertEquals(sol1.countDiceProb(3), sol2.countDice(3));
        assertEquals(sol1.countDiceProb(4), sol2.countDice(4));
        assertEquals(sol1.countDiceProb(5), sol2.countDice(5));
        assertEquals(sol1.countDiceProb(6), sol2.countDice(6));
        assertEquals(sol1.countDiceProb(7), sol2.countDice(7));
    }

    @Test
    public void testCase2(){
        assertEquals(sol1.countDiceProbByDP(610), sol2.countDice(610));
    }
}
