package com.mine;

import com.mine.dp.direction.minpathsum.MinPathSum_1D_inline;
import com.mine.dp.direction.minpathsum.MinPathSum_2D;
import com.mine.dp.direction.minpathsum.MinPathSum_1D_clone;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMinPathSum {
    private static MinPathSum_2D sol = new MinPathSum_2D();
    private static MinPathSum_1D_clone sol2 = new MinPathSum_1D_clone();
    private static MinPathSum_1D_inline sol3 = new MinPathSum_1D_inline();
    @Test
    public void testCase1(){
        assertEquals(7, sol.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        assertEquals(12, sol.minPathSum(new int[][]{{1,2,3}, {4,5,6}}));
    }

    @Test
    public void testCase2(){
        assertEquals(7, sol2.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        assertEquals(12, sol2.minPathSum(new int[][]{{1,2,3}, {4,5,6}}));
    }

    @Test
    public void testCase3(){
        assertEquals(7, sol3.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        assertEquals(12, sol3.minPathSum(new int[][]{{1,2,3}, {4,5,6}}));
    }
}
