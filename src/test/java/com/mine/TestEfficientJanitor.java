package com.mine;

import com.mine.greedy.BoatsToSavePpl;
import com.mine.greedy.EfficientJanitor;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestEfficientJanitor {
    private static EfficientJanitor sol = new EfficientJanitor();
    @Test
    public void testCase1(){
        assertEquals(3, sol.efficientJanitor(Arrays.asList(1.01f, 1.01f, 1.01f, 1.01f, 1.01f, 1.01f)));
        assertEquals(3, sol.efficientJanitor(Arrays.asList(1.01f, 1.01f, 1.01f,1.4f, 2.4f)));
    }
}
