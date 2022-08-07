package com.mine;

import com.mine.backtracking.permutation.Permutation_01;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TestPermutation {
    private static Permutation_01 sol = new Permutation_01();


    @Test
    public void testCaseWith2DArrayEquality(){
        int[][] eArr = new int[][]{
                {1,2,3},
                {1,3,2},
                {2,1,3},
                {2,3,1},
                {3,1,2},
                {3,2,1}};
        List<List<Integer>> actual = sol.permute(new int[]{1,2,3});

        /**
         * 1. sort 2D array - Arrays.sort(int[][], comparator)
         * 2. Arrays.deepEquals()
         */
        Comparator<int[]> comparator = (arr1, arr2)->{
            for(int i=0;i<arr1.length;i++){
                if(arr1[i]==arr2[i]){
                    continue;
                }
                return arr2[i] - arr1[i];
            }

            return 0;
        };
        Arrays.sort(eArr, comparator);


        /**
         * IntFunction<A[]> generator
         * * The generator function takes an integer, which is the size of the
         *      * desired array, and produces an array of the desired size.  This can be
         *      * concisely expressed with
         *              =>> an array constructor reference: e.g int[][]::new
         */
        int[][] aArr = actual.stream().map(i->i.stream()
                .mapToInt(Integer::intValue)
                .toArray())
                .toArray(int[][]::new);
        Arrays.sort(aArr, comparator);

        assertTrue(Arrays.deepEquals(eArr,aArr));
    }

    @Test
    public void testCaseWith2DCollectionEquality(){
        /**
         * Ordered
         * List Equality
         * two lists are defined to be
         *      * equal if they contain the same elements in the same order.
         *
         * Un-ordered
         * Set Equality
         * the two sets
         *      * have the same size, and every member of the specified set is
         *      * contained in this set (or equivalently, every member of this set is
         *      * contained in the specified set).
         *
         */
        int[][] eArr = new int[][]{
                {1,2,3},
                {1,3,2},
                {2,1,3},
                {2,3,1},
                {3,1,2},
                {3,2,1}};
        Set<List<Integer>> elist = Arrays.stream(eArr).map(i-> Arrays.stream(i)
                .boxed()
                .collect(Collectors.toList()))
                .collect(Collectors.toSet());

        List<List<Integer>> actual = sol.permute(new int[]{1,2,3});
        Set<List<Integer>> alist = actual.stream().collect(Collectors.toSet());
        assertEquals(elist,alist);
    }
}
