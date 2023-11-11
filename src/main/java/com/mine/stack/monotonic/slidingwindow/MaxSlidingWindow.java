package com.mine.stack.monotonic.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {
    /**
     * You are given an array of integers nums,
     * there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window.
     * Each time the sliding window moves right by one position.
     *
     * Return the max sliding window.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        final int n = nums.length;
        /*
            we must return the correct size of the array
            [1 2 3 4] and k = 2
            [1 2] [2 3] [3 4]
            size of the result = 4 - 2 + 1 = 3
         */
        if(n<k){
            throw new IllegalArgumentException();
        }
        int j=0;
        int[] result = new int[n-k+1];
        /*
            Cautious: deque.push() is equivalent to addFirst()
                      which adds element onto the head of the deque (double-ended queue)

                      Thus, reversed side of the deque is called 'Last' or 'Tail' when we think of stack
         */

        /*
            Optional:
                1. you can set initial size of deque = n
                2. Or, Actually, we will never pile up to exceed the size k
                    why ? similar to sliding window algo, we shrink/pop the last element
                    whenever the index range is greater than "k-1" before we insert a new element
         */
        /*
            Takeaway:
                Q: How does it differ from sliding window algo ?
                A: we're used to use sliding window algo
                   when we're gonna find the max/min size of subarray of max/min delta in a subarray,
                   in which fulfills our constraints. But it is not likely to fixed-size of subarray.
         */
        Deque<Integer> descStack = new ArrayDeque<>(k);
        for(int i=0;i<n;i++){
            while (!descStack.isEmpty()
                    && nums[i] > nums[descStack.peek()]) {
                descStack.pop();
            }

            while(!descStack.isEmpty()
                    && i-descStack.getLast()>=k){ //if index range overflow
                descStack.removeLast(); //shrink
            }

            descStack.push(i); //size of stack will never exceed (<=k)

            if(i+1>=k){
                result[j++]=nums[descStack.getLast()];
            }
        }

        return result;
    }
}
