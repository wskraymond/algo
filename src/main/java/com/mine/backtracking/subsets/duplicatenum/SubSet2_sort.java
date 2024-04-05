package com.mine.backtracking.subsets.duplicatenum;

import java.util.*;

public class SubSet2_sort {
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
        if(i==n){
            result.add(new ArrayList<>(subSet));    //O(n)
            return;
        }

        int num = nums[i];
        subSet.addLast(num);    //take
        backtrack(i+1, n, nums, subSet, result);
        subSet.removeLast();    //backtrack

        /*
        while(i<n && num==nums[++i]); <= failed
        # it causes index out of bound

        #what does ++/-- work in compiler
        #https://en.cppreference.com/w/cpp/language/operator_incdec
        int& prefix_++(int& i){
            i = i+1;
            return i;
        }

        int postfix_++(int& i){
            int x = i;
            i=i+1;
            return x;
        }

        i=1
        System.out.println(i++ + i + i); //output=1+2+2=5

        Alternative :
            #step 1:
                do{ i++; }while(i<n && num==nums[i]);
                    or
                while(++i<n && num==nums[i]);
            #step 2:
                backtrack(i, n, nums, subSet, result); // i here will be the index of next new one
        */
        while(i+1<n && num==nums[i+1]){
            i++;
        }

        //beware: i = the last index at which value = num (same value)
        //the index of next new number = i + 1
        backtrack(i+1, n, nums, subSet, result);
    }
}
