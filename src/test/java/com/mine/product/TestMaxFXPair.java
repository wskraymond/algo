package com.mine.product;

import org.junit.Test;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMaxFXPair {
    private static MaxFXPair_backtracking sol1 = new MaxFXPair_backtracking();
    private static MaxFXPair_bf sol2 = new MaxFXPair_bf();

    /**
     * Test case/Edge cases
     *      1. A <-> B <-> C <-> A ,
     *      given A->B->C is increasing multiplication and C -> A is decreasing multiplication
     *          a) for from A to C
     *              i) A -> B -> C
     *                  - it can lead to a cycle: A -> B -> C -> A -> C with decreasing cycle
     *              ii) A -> C
     *          b) for from B to A
     *              i) B -> A
     *                  -> it can lead to multiply 1/rate And backward of the fx fair conversion
     *              ii) B -> C -> A
     *
     *      2. A <> B
     *      3. null
     */

    @Test
    public void testMissingPairs(){
        Map<String, Double> fxFairs = new HashMap<>();
        fxFairs.put("A=B", 2d);
        assertEquals(-1d, sol1.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertEquals(-1d, sol1.maxProduct(fxFairs, "C", "A"), 0.00001);

        assertEquals(-1d, sol2.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertEquals(-1d, sol2.maxProduct(fxFairs, "C", "A"), 0.00001);
    }

    @Test
    public void testOnePair(){
        Map<String, Double> fxFairs = new HashMap<>();
        //A->B->A
        //2*1/2 = 1 , log(1)=0
        fxFairs.put("A=B", 2d);
        assertEquals(2d, sol1.maxProduct(fxFairs, "A", "B"), 0.00001);
        assertEquals(0.5d, sol1.maxProduct(fxFairs, "B", "A"), 0.00001);

        assertEquals(2d, sol2.maxProduct(fxFairs, "A", "B"), 0.00001);
        assertEquals(0.5d, sol2.maxProduct(fxFairs, "B", "A"), 0.00001);
    }

    /**
     * to test an unsupported graph with a negative cycle
     *      expected behaviour: Algo should only go through n-1 edges
     */
    @Test
    public void testFXPairsWIthOnlyPositiveCycleAndDifferentLength(){
        Map<String, Double> fxFairs = new HashMap<>();
        fxFairs.put("A=B", 2d);
        fxFairs.put("B=C", 3d);
        fxFairs.put("A=D", 4d);
        fxFairs.put("D=C", 4d);
        fxFairs.put("A=C", 8d);

        assertEquals(16d, sol1.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertEquals(1/6d, sol1.maxProduct(fxFairs, "C", "A"), 0.00001);

        assertEquals(16d, sol2.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertEquals(1/6d, sol2.maxProduct(fxFairs, "C", "A"), 0.00001);
    }

    /**
     * to test an unsupported graph with a negative cycle
     *      expected behaviour: Algo should only go through n-1 edges
     */
    @Test
    public void testFXPairsWIthANegativeCycleAndDifferentLength(){
        Map<String, Double> fxFairs = new HashMap<>();
        fxFairs.put("A=B", 2d);
        fxFairs.put("B=C", 3d);
        fxFairs.put("A=D", 4d);
        fxFairs.put("D=C", 4d);
        fxFairs.put("A=C", 8d);
        fxFairs.put("X=D", 0.1d);
        fxFairs.put("A=Y", 8d);


        assertEquals(1/8d*4*10, sol1.maxProduct(fxFairs, "Y", "X"), 0.00001);
        //Y->A->C->B->A->D->X = 6.666666
        //A->C->B->A : 8*1/3*1/2 = 1.333 (negative cycle)
        assertNotEquals(1/8d*8*1/3*1/2*4*10, sol1.maxProduct(fxFairs, "Y", "X"), 0.00001);
        assertEquals(0.1*4*1/3*1/2*8, sol1.maxProduct(fxFairs, "X", "Y"), 0.00001);

        assertEquals(1/8d*4*10, sol2.maxProduct(fxFairs, "Y", "X"), 0.00001);
        assertNotEquals(1/8d*8*1/3*1/2*4*10, sol2.maxProduct(fxFairs, "Y", "X"), 0.00001);
        assertEquals(0.1*4*1/3*1/2*8, sol2.maxProduct(fxFairs, "X", "Y"), 0.00001);
    }

    @Test
    public void testFXPairsWIthOnlyPositiveCycleAndTwoSameLenghthWithACycleAcrossPairs(){
        Map<String, Double> fxFairs = new HashMap<>();
        //given that A->B->C is increasing multiplication and C -> A is decreasing multiplication
        //it can lead to a cycle: A -> B -> C -> A - with decreasing cycle
        //2 * 3 * 0.1 = 0.6 ,  log(0.6) = negative
        //Thus , the input is pair chains with only decreasing cycles

        //log(2) = 0.30102 => negate to -0.30102
        //log(1/2) = -0.30102 => negate to 0.30102
        fxFairs.put("A=B", 2d);
        //log(3) = 0.47712 => negate to -0.47712
        //log(1/3) = -0.47712 => negate to 0.47712
        fxFairs.put("B=C", 3d);
        //log(1/6) = -0.778151 => negate to 0.778151
        //log(6) = 0.778151 => negate to -0.778151
        fxFairs.put("C=A", 1/6d);

        //A->B->C->A
        //2*3*1/6 = 1
        //-0.30102 -0.47712 + 0.778151 = 0 (a positive cycle)

        //A->C
        //6
        //A->C->B->A             ->C
        //6*1/3*1/2=1      * 10 = 16.66666
        //-0.778151 + 0.47712 + 0.30102 = 0 (a negative cycle)
        assertEquals(6, sol1.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertEquals(6, sol2.maxProduct(fxFairs, "A", "C"), 0.00001);
        //B->C->A->B
        //3*1/6*2=1
        //B->A->C->B
        //1/2*6*1/3=1
        assertEquals(0.5, sol1.maxProduct(fxFairs, "B", "A"), 0.00001);
        assertEquals(0.5, sol2.maxProduct(fxFairs, "B", "A"), 0.00001);
    }

    @Test
    public void testFXPairsWIthANegativeCycle(){
        Map<String, Double> fxFairs = new HashMap<>();
        //given that A->B->C is increasing multiplication and C -> A is decreasing multiplication
        //it can lead to a cycle: A -> B -> C -> A - with decreasing cycle
        //2 * 3 * 0.1 = 0.6 ,  log(0.6) = negative
        //Thus , the input is pair chains with only decreasing cycles

        //log(2) = 0.30102 => negate to -0.30102
        //log(1/2) = -0.30102 => negate to 0.30102
        fxFairs.put("A=B", 2d);
        //log(3) = 0.47712 => negate to -0.47712
        //log(1/3) = -0.47712 => negate to 0.47712
        fxFairs.put("B=C", 3d);
        //log(0.1) = -1 => negate to 1
        //log(10) = 1 => negate to -1
        fxFairs.put("C=A", 0.1);

        //A->B->C->A
        //2*3*0.1 = 0.6
        //-0.30102 -0.47712 + 1 = 0.22186 (a positive cycle)

        //A->C
        //1/0.1=10
        //A->C->B->A             ->C
        //10*1/3*1/2=1.6666      * 10 = 16.66666
        //-1 + 0.47712 + 0.30102 = -0.22186 (a negative cycle)
        assertNotEquals(10.0*1/3.0*1/2.0*10.0, sol1.maxProduct(fxFairs, "A", "C"), 0.00001);
        assertNotEquals(10.0*1/3.0*1/2.0*10.0, sol2.maxProduct(fxFairs, "A", "C"), 0.00001);
        //B->C->A->B->C->A
        //3*0.1*2=0.6*3*0.1=0.18
        //B->A->C->B->A
        //1/2*10*1/3*1/2 = 0.83
        assertNotEquals(0.83d, sol1.maxProduct(fxFairs, "B", "A"), 0.00001);
        assertNotEquals(0.83d, sol2.maxProduct(fxFairs, "B", "A"), 0.00001);
    }
}
