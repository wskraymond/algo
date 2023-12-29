package com.mine.stack.monotonic.slidingwindow;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class MaxSlidingWindow_heapsort {
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

        int[] result = new int[n-k+1];
        Queue<int[]> maxHeap = new PriorityQueue<>(2*k, Comparator.comparingInt(e->-e[1]));
        for(int i=0, j=0;i<n;i++){  //O(n)
            maxHeap.offer(new int[]{i,nums[i]});

            while(j>maxHeap.peek()[0]){ //O(log(a*k)) for 0 < a <= 2
                maxHeap.poll();
            }

            if(i+1>=k){
                result[j++]=maxHeap.peek()[1];
            }
        }

        return result; //O(n*log(k))
    }
}
