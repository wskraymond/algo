package com.mine.jpm.permutation;

import java.util.*;
import java.util.stream.Collectors;

public class Permutation_01 {
    /**
     * Given an array nums of distinct integers,
     * return all the possible permutations.
     * You can return the answer in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * Example 2:
     *
     * Input: nums = [0,1]
     * Output: [[0,1],[1,0]]
     *
     * Example 3:
     *
     * Input: nums = [1]
     * Output: [[1]]
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 6
     *     -10 <= nums[i] <= 10
     *     All the integers of nums are unique.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        /**
         * using LinkedHashSet
         *    - support the ordering of permutation - O(1)
         *    - support O(1) searching
         */
        backtrack(0, nums, new LinkedHashSet<>(), result);

        return result;
    }

    public void backtrack(int r,int[] nums, Set<Integer> visit, List<List<Integer>> result){
        if(r==nums.length){ //required number of candidates in the solution is nums.length
            result.add(visit.stream().collect(Collectors.toList()));
        }

        for(int i=0;i<nums.length;i++){
            //state
            //candidate-> nums[i]
            int c=nums[i];

            //isValid
            if(visit.contains(c)){
                continue;
            }

            visit.add(c);

            backtrack(r+1, nums, visit, result);

            //backtrack by removing c
            visit.remove(c);
        }
    }
}
