package com.mine.stack.monotonic.sumsubarraymins;

import java.util.ArrayDeque;
import java.util.Deque;

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
        if(arr.length==0){
            return 0;
        }

        /*
            failed to handle duplicate number in array
            e.g [71,55,82,55]
         */

        int n = arr.length;
        int[] nextRightSmallerIndexArr = new int[n];
        Deque<Integer> descStack = new ArrayDeque<>(n);
        for(int i=n-1;i>=0;i--){
            int currVal = arr[i];
            while(!descStack.isEmpty()
                    && currVal<=arr[descStack.peek()]){
                descStack.pop();
            }

            nextRightSmallerIndexArr[i] = descStack.isEmpty() ? n : descStack.peek();
            descStack.push(i);
        }

        descStack.clear();

        int[] nextLeftSmallerIndexArr = new int[n];
        for(int j=0;j<n;j++){
            int currVal = arr[j];
            while(!descStack.isEmpty()
                    && currVal<arr[descStack.peek()]){
                descStack.pop();
            }

            nextLeftSmallerIndexArr[j] = descStack.isEmpty() ? -1 : descStack.peek();
            descStack.push(j);
        }

        final int M = (int)1e9 + 7;
        long sum = 0;
        for(int i=0;i<n;i++){
            int min = arr[i];
            //left index and right index of the array which contains all subarrays sharing the same min
            int left = nextLeftSmallerIndexArr[i];
            int right = nextRightSmallerIndexArr[i];

            long numOfSameMin = (i -left)*(right-i);
            sum=(sum+min*numOfSameMin)%M;
        }

        return (int)sum;
    }
}
