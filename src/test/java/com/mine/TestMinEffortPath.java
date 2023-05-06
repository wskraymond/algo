package com.mine;

import com.mine.backtracking.directions.mineffortpath.MinEffortPath_backtrack_but_still_LTE;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMinEffortPath {
    private static MinEffortPath_backtrack_but_still_LTE sol = new MinEffortPath_backtrack_but_still_LTE();
    @Test
    public void testCase1(){
        assertEquals(2, sol.minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}}));
    }
}
