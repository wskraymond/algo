package com.mine.backtracking.subsets.duplicatenum;

import java.util.*;

public class SubSet2_iterative {
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
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);  //nlogn
        backtrack(0,
                nums.length,
                nums,
                new LinkedList<>()
                , result
        );  //n*(2^n)

        return result;  //O(nlogn) + O(n*(2^n)) = exponential
    }

    private void backtrack(int i, int n, int[] nums, Deque<Integer> subSet, List<List<Integer>> result){
        //for every new candidate , which is added to previous unique set
        result.add(new ArrayList<>(subSet));    //O(n)

        for(int x=i;x<n;x++) {
             int num=nums[x];
             subSet.addLast(num);
             backtrack(x+1, n, nums, subSet, result);
             subSet.removeLast();
             while (x+1<n && num==nums[x+1]){
                 x++;
             }
        }
    }
}
