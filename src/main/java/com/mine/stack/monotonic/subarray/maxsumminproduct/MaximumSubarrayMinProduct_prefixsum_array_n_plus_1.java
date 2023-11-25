package com.mine.stack.monotonic.subarray.maxsumminproduct;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class MaximumSubarrayMinProduct_prefixsum_array_n_plus_1 {
    /**
     * https://leetcode.com/problems/maximum-subarray-min-product/
     * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
     *
     *     For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
     *
     * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
     * Since the answer may be large, return it modulo 109 + 7.
     *
     * Note that the min-product should be maximized
     * before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
     *
     * A subarray is a contiguous part of an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,2]
     * Output: 14
     * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
     * 2 * (2+3+2) = 2 * 7 = 14.
     *
     * Example 2:
     *
     * Input: nums = [2,3,3,1,2]
     * Output: 18
     * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
     * 3 * (3+3) = 3 * 6 = 18.
     *
     * Example 3:
     *
     * Input: nums = [3,1,5,6,4,2]
     * Output: 60
     * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
     * 4 * (5+6+4) = 4 * 15 = 60.
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 105
     *     1 <= nums[i] <= 107
     */
    public int maxSumMinProduct(int[] nums) {
        /*
            # Intuition
            iterate every element as a min of a subarray

            # Approach
            prefix sum + maximizing the widh of every element as a min of a subarray
            by knowing the next right and next left smaller
            of every popped element in a monotonic increasing stack

            # Complexity
            - Time complexity:
            O(0)

            - Space complexity:
            O(0)
         */
        if(nums.length==0){
            return 0;
        }
        final int n = nums.length, M = (int) (1e9+7);
        long max =0;

        //To make our life easier to handle the case that there is no left smaller number for the min
        //array of size=n+1 will be used to include prefixSum[0]=0
        final long[] prefixSum = new long[n+1];
        IntStream.range(1,prefixSum.length).forEach(i->prefixSum[i]=prefixSum[i-1]+nums[i-1]);

        Deque<Integer> descStack = new ArrayDeque<>(n);
        for(int i=0;i<n;i++){
            int nextRightIndex = i;
            //for the edge case : 2,6,5,3,3,5,6,2
            // if we pop
            // when inserting 2nd 3, if we pop 1st 3 -> (6+5+3)*3
            // when inserting 2, if we pop 2nd 3 -> (6+5+3+3+5+6)*3
            // if we don't pop 2nd 3
            // when inserting last 2, then pop 2nd 3 -> (3+3+5+6)*3
            //                        then pop 1st 3 ->  (6+5+3+3+5+6)*3
            while(!descStack.isEmpty()
                && nums[nextRightIndex] < nums[descStack.peek()]){
                long min = nums[descStack.pop()];
                int nextLeftIndex = descStack.isEmpty() ? -1 : descStack.peek();

                //beware: prefixSum[i] doesn't include nums[i].
                //inclusive prefixSum
                long rightPrefix = prefixSum[nextRightIndex];
                //beware: when stack is empty, the left prefix should be zero
                //exclsuive prefixSum
                long leftPrefix = prefixSum[nextLeftIndex+1];

                long product = min*(rightPrefix-leftPrefix);
                max = Math.max(max,product);
            }

            descStack.push(i);
        }

        /*
            don't forget to do the rest
         */
        while(!descStack.isEmpty()){
            long min = nums[descStack.pop()];
            int nextLeftIndex = descStack.isEmpty() ? -1 : descStack.peek();

            //beware: rightPrefix here should include all numbers
            long rightPrefix = prefixSum[n];
            long leftPrefix = prefixSum[nextLeftIndex+1];

            long product = min*(rightPrefix-leftPrefix);
            max = Math.max(max,product);
        }

        /*
         * Since the answer may be large, return it modulo 109 + 7.
         *
         * Note that the min-product should be maximized
         * before performing the modulo operation.
         * Testcases are generated such that the maximum min-product
         * without modulo will fit in a 64-bit signed integer.
         */

        return (int) (max%M);
    }

}
