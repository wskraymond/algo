package com.mine;

import com.mine.Intervals.merge.MergeInterval;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestMergeInterval {
    private static MergeInterval sol = new MergeInterval();
    @Test
    public void testCase1(){
        assertArrayEquals(new int[][]{{1,2}, {3,4}}, sol.merge(new int[][]{{1,2}, {3,4}}));
        assertArrayEquals(new int[][]{{1,4}}, sol.merge(new int[][]{{1,2}, {2,4}}));
        assertArrayEquals(new int[][]{{1,4}}, sol.merge(new int[][]{{1,4}, {1,2}}));
        assertArrayEquals(new int[][]{{1,6},{8,10},{15,18}}, sol.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
    }
}
