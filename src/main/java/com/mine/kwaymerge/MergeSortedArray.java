package com.mine.kwaymerge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeSortedArray {
    /**
     *
     * https://leetcode.com/problems/merge-sorted-array/
     *
     * You are given two integer arrays nums1 and nums2,
     * sorted in non-decreasing order, and two integers m and n,
     * representing the number of elements in nums1 and nums2 respectively.
     *
     * Merge nums1 and nums2 into a single array
     * sorted in non-decreasing order.
     *
     * The final sorted array should not
     * be returned by the function,
     *  - #but instead be stored inside the array nums1.
     *  - To accommodate this, nums1 has a length of m + n,
     *      - where the first m elements denote the elements that should be merged,
     *          and the last n elements are set to 0 and should be ignored.
     *          nums2 has a length of n.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     *
     * Example 2:
     *
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     *
     * Example 3:
     *
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     *
     *
     *
     * Constraints:
     *
     *     nums1.length == m + n
     *     nums2.length == n
     *     0 <= m, n <= 200
     *     1 <= m + n <= 200
     *     -109 <= nums1[i], nums2[j] <= 109
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0){
            return;
        }

        if(m==0){
            System.arraycopy(nums2,0, nums1, 0, nums2.length);
            return;
        }

        int[] result = new int[m+n];
        int i =0;
        int[][] nums = new int[][]{nums1, nums2};
        int[][] indices = new int[][]{{0,m},{0,n}};
        Queue<int[]> minHeap = new PriorityQueue<>(2, Comparator.comparingInt(num->num[0]));
        minHeap.add(new int[]{nums1[indices[0][0]++], 0});
        minHeap.add(new int[]{nums2[indices[1][0]++], 1});

        while(!minHeap.isEmpty()){
            int[] e = minHeap.poll();
            int num = e[0];
            int numIndex = e[1];
            result[i++] = num;
            if(indices[numIndex][0] < indices[numIndex][1]) {
                minHeap.add(new int[]{nums[numIndex][indices[numIndex][0]++], numIndex});
            }
        }

        System.arraycopy(result,0, nums1, 0, result.length);
    }
}
