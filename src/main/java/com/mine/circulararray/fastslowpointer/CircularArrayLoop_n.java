package com.mine.circulararray.fastslowpointer;

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop_n {
    /**
     * You are playing a game involving a circular array of non-zero integers nums.
     * Each nums[i] denotes the number of indices forward/backward
     * you must move if you are located at index i:
     *
     * If nums[i] is positive, move nums[i] steps forward, and
     * If nums[i] is negative, move nums[i] steps backward.
     * Since the array is circular,
     * you may assume that moving forward from the last element puts you on the first element,
     * and moving backwards from the first element puts you on the last element.
     *
     * A cycle in the array consists of a sequence of indices seq of length k where:
     *
     * Following the movement rules above results
     * in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
     * Every nums[seq[j]] is either all positive or all negative.
     *
     * k > 1
     * Return true if there is a cycle in nums, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,-1,1,2,2]
     * Output: true
     * Explanation:
     * There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
     * The cycle's length is 3.
     *
     * Example 2:
     *
     * Input: nums = [-1,2]
     * Output: false
     * Explanation:
     * The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence's length is 1.
     * By definition the sequence's length must be strictly greater than 1 to be a cycle.
     *
     * Example 3:
     *
     * Input: nums = [-2,1,-1,-2,-2]
     * Output: false
     * Explanation:
     * The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is positive, but nums[2] is negative.
     * Every nums[seq[j]] must be either all positive or all negative.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * -1000 <= nums[i] <= 1000
     * nums[i] != 0
     *
     *
     * Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        /**
         * Amortized Analysis:
         *    How do we know if it is averagely O(n)
         *    If not, how can we achieve it ?
         *      - use set to store the path which has no cycle fulfilling the requirement.
         *      - then we won't repeat what we have gone through....
         */
        Set<Integer> noCycle = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            boolean isForward = nums[i]>=0;
            int s = i, f = i;
            Set<Integer> path = new HashSet<>();
            do{
                /**
                 * why must it end with either a cycle with all the same direction
                 * or end with opposite direction ?
                 * instead of infinitely increasing index ?
                 */
                s = nextIndex(isForward, s, nums, path, noCycle);
                f = nextIndex(isForward, f, nums, path, noCycle);
                f = nextIndex(isForward, f, nums, path, noCycle);
            } while(s!=-1&&f!=-1&&s!=f);

            //check result
            if(s==f && s!=-1 && f!=-1){
                return true;
            } else {
                noCycle.addAll(path);
            }
        }

        return false;
    }

    private int nextIndex(boolean isForward, int curr, int[] nums, Set<Integer> path, Set<Integer> noCycle){
        if(curr<0){
            return -1;
        }

        boolean direction = nums[curr] >=0;
        if(direction!=isForward){
            return -1;
        }

        if(noCycle.contains(curr)){
            return -1;
        }

        int nextSteps = nums[curr];
        int nextIndex = (curr+nextSteps)%nums.length;

        //edge case 1:  curr+nextSteps can be negative
        if(nextIndex<0){
            nextIndex += nums.length;
        }

        path.add(curr);

        //edge case 2: what if nextIndex equals currentIndex
        //A cycle in the array consists of a sequence of indices seq of length k where: K > 1
        if(curr==nextIndex){
            return -1;
        }

        return nextIndex;
    }
}
