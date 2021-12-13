package com.mine.graph.dfs;

import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAllPaths {
    private static AllPaths_NON_DAG sol = new AllPaths_NON_DAG();
    @Test
    public void testCase1(){
        /**
         * Following are all different paths from 2 to 3
         * 2 0 1 3
         * 2 0 3
         * 2 1 3
         */
        int[][] eArr = new int[][]{
                {2,0,3},
                {2,0,1,3},
                {2,1,3}
        };
        List<List<Integer>> actual = sol.allPaths(2, 3, new int[][]{
                {1,2,3},
                {3},
                {0,1},
                {}
        });

        actual.forEach(System.out::println);

        /**
         * 1. sort 2D array - Arrays.sort(int[][], comparator)
         * 2. Arrays.deepEquals()
         */
        Comparator<int[]> comparator = (arr1, arr2)->{
            int min = Math.min(arr1.length, arr2.length);
            for(int i=0;i<min;i++){
                if(arr1[i]==arr2[i]){
                    continue;
                }
                return arr2[i] - arr1[i];
            }

            return arr2.length - arr1.length;
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
}
