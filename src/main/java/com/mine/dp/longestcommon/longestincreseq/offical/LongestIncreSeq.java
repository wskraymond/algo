package com.mine.dp.longestcommon.longestincreseq.offical;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LongestIncreSeq {
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
        //dp[i]: max size of increasing sequence ends at index i
        //thus, we have N number of sequence which ends at index i
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        dp(nums, dp, null);

        return Arrays.stream(dp).max().getAsInt();
    }

    public int[] optimalSol(int[] nums) {
        //dp[i]: max size of increasing sequence ends at index i
        //thus, we have N number of sequence which ends at index i
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //store the best/optimal choice - parent pointer
        //parent[i]=x: the subproblem/vertex(here is number x) where the number at index i transited from....
        int[] parent = new int[nums.length];
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }

        dp(nums, dp, parent);

        int max=0;
        int endIndex=-1;
        for(int i=0;i<dp.length;i++){
            if(dp[i]> max) {
                max = dp[i];
                endIndex = i;
            }
        }

        Deque<Integer> sol = new LinkedList<>();
        int currentIndex = endIndex;
        int parentIndex = parent[currentIndex];
        sol.addFirst(nums[currentIndex]);
        while (currentIndex!=parentIndex) {
            sol.addFirst(nums[parentIndex]);
            currentIndex = parentIndex;
            parentIndex = parent[currentIndex];
        }

        int[] solArr = sol.stream().mapToInt(Integer::intValue).toArray();

        return solArr;
    }

    private void dp(int[] nums, int[] dp, int[] parent) {

    }
}
