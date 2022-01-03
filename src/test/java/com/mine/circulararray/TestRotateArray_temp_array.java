package com.mine.circulararray;

import com.mine.circulararray.rotate.RotateArray_temp_array;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestRotateArray_temp_array {
    private static RotateArray_temp_array sol = new RotateArray_temp_array();

    @Test
    public void testSingleElement(){
        int[] actual = new int[]{1};
        sol.rotate(actual, 3);
        assertArrayEquals(new int[]{1}, actual);
    }

    @Test
    public void testCase1(){
        int[] actual = new int[]{1,2,3,4,5,6,7};
        sol.rotate(actual, 3);
        assertArrayEquals(new int[]{5,6,7,1,2,3,4}, actual);
    }

    @Test
    public void testCase2(){
        int[] actual = new int[]{-1,-100,3,99};
        sol.rotate(actual, 2);
        assertArrayEquals(new int[]{3,99,-1,-100}, actual);
    }
}
