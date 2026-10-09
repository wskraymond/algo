package com.practice.lc;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<Integer, Long> countMap = Arrays.stream(nums).mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int[] keys = countMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        List<List<Integer>> result = new ArrayList<>();
        dfs(0,keys, countMap, new ArrayDeque<>(), result);
        return result;
    }

    public void dfs(int i , int[] keys, Map<Integer,Long> countMap, Deque<Integer> subset, List<List<Integer>> result){
        //base case
        if(i==keys.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        //take number at i
        int num = keys[i];
        subset.add(num);
        long remaining = countMap.merge(num, -1L , Long::sum);
        dfs(remaining>0 ? i : i+1, keys, countMap, subset, result);

        //backtrack
        subset.removeLast();
        countMap.merge(num, +1L, Long::sum);

        //skip at i
        dfs(i+1, keys, countMap, subset, result);
    }

}
