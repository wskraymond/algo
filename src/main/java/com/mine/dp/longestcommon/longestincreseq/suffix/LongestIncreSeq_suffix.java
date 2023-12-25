package com.mine.dp.longestcommon.longestincreseq.suffix;

import java.util.Arrays;

public class LongestIncreSeq_suffix {
    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/
     * or
     *
     * variance of problem: https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
     *
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     *
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        /*
            0. Time Complexity
                #sub-problems x #guess + cost(combining subproblem)
            1. define a subproblem
                f(i) = return the length of longest increasing subseq in which the head is at index i for the suffix[i:]
            2. relates subproblems by guessing
                Optimal Structure (Recurrence Relations)
                f(i) = Max{ f(j) + 1
                                | if nums[i]< nums[j] for j=i+1 ... n-1
                            , 1 }
                Base case:  initialize f(i) = 0
                        (alternatively if don't compare with 1) initialize f(i) = 1
            3. recursion & memo
                TopOrder: from n-1 to 0
            4. combining subproblem
               LIS = Max{f(i)}
            5. parent pointers
                which guess is used for each f(i)
         */
        final int n = nums.length;
        int[] dp = new int[n];
        for(int i=n-1;i>=0;i--){
            dp[i] = 1;
            for(int j=i+1;j<n;j++){
                if(nums[i]<nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
