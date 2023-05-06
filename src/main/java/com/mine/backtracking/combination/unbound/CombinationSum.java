package com.mine.backtracking.combination.unbound;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSum {
    /**
     * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
     * frequency
     * of at least one of the chosen numbers is different.
     *
     * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
     *
     *
     *
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * Explanation:
     * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
     * 7 is a candidate, and 7 = 7.
     * These are the only two combinations.
     *
     * Example 2:
     *
     * Input: candidates = [2,3,5], target = 8
     * Output: [[2,2,2,2],[2,3,3],[3,5]]
     *
     * Example 3:
     *
     * Input: candidates = [2], target = 1
     * Output: []
     *
     *
     *
     * Constraints:
     *
     *     1 <= candidates.length <= 30
     *     2 <= candidates[i] <= 40
     *     All elements of candidates are distinct.
     *     1 <= target <= 40
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
            take&skip recursive function - backtracking
            Recurrence Relation:
                f(i,s) = f(i,s-i) And f(i+1,s)
            Base Case:
                f(i,0) => if s==0 then add to result and return
                f(i, s<0) => if s<0 then return
                f(0,s) => if i==n then return (no more candidates)
         */
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, target, candidates, new LinkedList<>(), result);
        return result;
    }

    public void backtrack(int i , int s, int[] candidates, Deque<Integer> combination, List<List<Integer>> result){
        if(s==0){
            result.add(combination.stream().collect(Collectors.toList()));
            return;
        }

        if(s<0 || i==candidates.length){
            return;
        }

        //for sure, we can also use candidate-iterative backtracking
        //here, just write in an alternative way
        int candidate = candidates[i];
        combination.add(candidate);
        backtrack(i, s-candidate,candidates, combination, result);
        combination.removeLast();
        backtrack(i+1, s, candidates, combination, result);

        return;
    }
}
