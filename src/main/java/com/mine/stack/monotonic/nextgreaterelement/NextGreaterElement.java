package com.mine.stack.monotonic.nextgreaterelement;

import java.util.*;
import java.util.stream.IntStream;

public class NextGreaterElement {
    /**
     * https://leetcode.com/problems/next-greater-element-i/description/
     *
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     *
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     *
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
     *
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Output: [-1,3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
     * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * Example 2:
     *
     * Input: nums1 = [2,4], nums2 = [1,2,3,4]
     * Output: [3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
     * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
     *
     *
     * Constraints:
     *
     * 1 <= nums1.length <= nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 104
     * All integers in nums1 and nums2 are unique.
     * All the integers of nums1 also appear in nums2.
     *
     *
     * Follow up: Could you find an O(nums1.length + nums2.length) solution?
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
         * All integers in nums1 and nums2 are unique.
         * All the integers of nums1 also appear in nums2.
         *
         *
         * Follow up: Could you find an O(nums1.length + nums2.length) solution?
         *
         * Return an array ans of length nums1.length such that ans[i] is the next greater element
         */

        /*
            the next greater element on the right = push element from right to left on the array + increasing monotonic stack
         */
        if(nums1.length==0){
            return new int[0];
        }

        Deque<Integer> ascStack = new ArrayDeque<>();
        Map<Integer,Integer> nextGtMap = new HashMap<>();
        for(int j=nums2.length-1;j>=0;j--){ //O(m)
            int currVal = nums2[j];
            while(!ascStack.isEmpty()
                    && currVal>=ascStack.peek()){
                ascStack.pop();//elements being popped are in ascending order
            }
            int nextGtVal = ascStack.isEmpty() ? -1 : ascStack.peek();
            nextGtMap.put(currVal, nextGtVal);
            ascStack.push(currVal);
        }

        /*
            for no next greater element, either of belows can be used , but here it shows both.
                1) stack.isEmpty then either store -1 in the map
                    or store nothing and have 2)
                2) getOrDefault() when converting to result
         */

        return Arrays.stream(nums1).map(currVal->nextGtMap.getOrDefault(currVal, -1)).toArray(); //O(n)
        //total = O(m+n)
    }
}
