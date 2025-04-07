package com.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubSet2 {
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
        Map<Integer,Long> countMap = Arrays.stream(nums)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        List<List<Integer>> result = new LinkedList<>();
        dfs(0, countMap.keySet().stream().mapToInt(Integer::intValue).toArray(), countMap, new LinkedList<>(), result);
        return result; //O(n*(2^n)) + O(2*n)
    }

    public void dfs(int i , int[] keys, Map<Integer,Long> countMap, Deque<Integer> subset, List<List<Integer>> result){
        if(i==keys.length){
            result.add(new ArrayList<>(subset));
        }

        int num = keys[i];

        //take
        long newCount = countMap.merge(num, -1L,Long::sum);
        subset.add(num);
        dfs(newCount>0 ? i : i+1, keys, countMap, subset, result);
        countMap.merge(num, +1L, Long::sum);
        subset.removeLast();

        //skip
        dfs(i+1, keys, countMap, subset, result);
    }

}
