package com.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

public class SumSubarrayMins {
    /**
     * https://leetcode.com/problems/sum-of-subarray-minimums/
     *
     * Given an array of integers arr,
     * find the sum of min(b),
     * where b ranges over every (contiguous) subarray of arr.
     * Since the answer may be large, return the answer modulo 109 + 7.
     *
     * Example 1:
     *
     * Input: arr = [3,1,2,4]
     * Output: 17
     * Explanation:
     * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
     * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
     * Sum is 17.
     *
     * Example 2:
     *
     * Input: arr = [11,81,94,43,3]
     * Output: 444
     *
     * Constraints:
     *
     *     1 <= arr.length <= 3 * 104
     *     1 <= arr[i] <= 3 * 104
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        final int M = (int)1e9+7;
        /*
            1. count of same min shared by all ranged subarray = (minIndex - next left smaller)*(next right smaller - minIndex)
                a) use of monotonic increasing stack of index, and iterate from left to right
                b) minIndex = pop() rs = i , ls=top() after pop()
            2. avoid double counting for duplicate value in the same subarr
                a) only includes duplicate value into subarray on either left or right side of minIndex
                b) arr[i]<=arr[i+k]
            3. sum+=min*count
         */
        final int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        long sum =0;
        for(int i=0;i<n;i++){
            int num = arr[i];
            while(!stack.isEmpty() &&
                    num < arr[stack.peek()]){
                int minIndex = stack.pop();
                int rs = i;
                int ls = stack.isEmpty() ? -1 : stack.peek();
                int min = arr[minIndex];
                sum+=(long)min*(minIndex-ls)*(rs-minIndex);
            }

            stack.push(i);
        }

        //remaining element when there is no next right smaller for its min
        while(!stack.isEmpty()){
            int minIndex = stack.pop();
            int rs = n;
            int ls = stack.isEmpty() ? -1 : stack.peek();
            int min = arr[minIndex];
            sum+=(long)min*(minIndex-ls)*(rs-minIndex);
        }

        return (int)(sum%M);
    }
}
