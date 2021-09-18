package com.mine;

import com.mine.backtracking.TotalNQueens;
import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTotalNQueens {
    private static TotalNQueens sol = new TotalNQueens();
    @Test
    public void testCase1(){
        assertEquals(1, sol.totalNQueens(1));
        assertEquals(0, sol.totalNQueens(2));
        assertEquals(2, sol.totalNQueens(4));
    }
}
