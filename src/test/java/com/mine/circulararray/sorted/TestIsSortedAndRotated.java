package com.mine.circulararray.sorted;

import com.mine.circulararray.rotate.RotateArray_reverse;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestIsSortedAndRotated {
    private static IsSortedAndRotated sol = new IsSortedAndRotated();

    @Test
    public void testSingleElement(){
        assertEquals(true, sol.check(new int[]{1}));
    }

    @Test
    public void testAllDuplicates(){
        assertEquals(true, sol.check(new int[]{1,1,1,1}));
    }

    @Test
    public void testOnly2UniqueDigitsunrotatedSortedArray(){
        assertEquals(true, sol.check(new int[]{1,1,2,2}));
    }

    @Test
    public void testOnly2UniqueDigitsRotatedSortedArray(){
        assertEquals(true, sol.check(new int[]{2,1,1,2}));
        assertEquals(true, sol.check(new int[]{2,2,1,1}));
        assertEquals(true, sol.check(new int[]{1,2,2,1}));
    }

    @Test
    public void testOnly2UniqueDigitsUnsortedArray(){
        assertEquals(false, sol.check(new int[]{1,2,1,2}));
        assertEquals(false, sol.check(new int[]{2,1,2,1}));
    }

    @Test
    public void testUnrotatedSortedArray(){
        assertEquals(true, sol.check(new int[]{1,2,3,4,5}));
    }

    @Test
    public void testRotatedSortedArray(){
        assertEquals(true, sol.check(new int[]{3,4,5,1,2}));
    }

    @Test
    public void testUnrotatedUnsortedArray(){
        assertEquals(false, sol.check(new int[]{1,6,3,4,5}));
    }

    @Test
    public void testRotatedUnsortedArray(){
        assertEquals(false, sol.check(new int[]{3,4,5,1,6}));
    }
}
