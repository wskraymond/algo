package com.mine.backtracking.subsets;

import java.util.*;

public class SubSet2_hashmap_iterative {
    /**
     * Given an integer array nums that may contain duplicates, return all possible
     * subsets
     * (the power set).
     *
     * The solution set must not contain duplicate subsets.
     * Return the solution in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 10
     *     -10 <= nums[i] <= 10
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /*
            1. The solution set must not contain duplicate subsets
            2. Return the solution in any order
         */
        /*
            map.merge()
            If the specified key is not already associated with a value
            or is associated with null, associates it with the given non-null value.
            Otherwise, replaces the associated value with the results of the given remapping function,
            or removes if the result is nul
         */
        List<List<Integer>> result = new LinkedList<>();
        Map<Integer,Integer> m = new HashMap<>();
        final int n = nums.length;
        for(int i=0;i<n;i++){ //O(n)
            m.merge(nums[i], 1, Integer::sum);
        }

        backtrack(0,
                m.keySet().stream()
                        .mapToInt(Integer::intValue)
                        .toArray(),     //O(n)
                m,
                new LinkedList<>()
                , result
        );

        return result; //O(n*(2^n)) + O(2*n)
    }

    private void backtrack(int i, int[] keys, Map<Integer, Integer> m, Deque<Integer> subSet, List<List<Integer>> result){
        if(i==keys.length){
            result.add(new ArrayList<>(subSet));    //O(n)
            return;
        }

        int num = keys[i];
        int newCount = m.merge(num, -1, Integer::sum); //O(1)
        subSet.addLast(num);
        backtrack(newCount>0 ? i:i+1, keys, m, subSet, result);
        subSet.removeLast();
        m.merge(num, 1, Integer::sum);  //O(1)

        backtrack(i+1, keys, m, subSet, result);

        //copy = O(n)
        //number of subset = O(2^n)
        //total = O(n*(2^n))
    }
}
