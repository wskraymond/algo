package com.mine.circulararray.rotate.others;

public class RotateArray_cyclic {
    /**
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * Example 2:
     *
     * Input: nums = [-1,-100,3,99], k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     *
     *
     * Follow up:
     *
     * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
     * Could you do it in-place with O(1) extra space?
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        for(int count=0, start=0;count<nums.length;start++){
            /*
                Do we need a for loop to check if all are in correct position ?
                    e.g nums=[-1,-100,3,99], k=2
                    one while loop is not enough for start=0 -> [3,-100,-1,99] //not yet completed

                Please ignore this solution becos it needs strong math proof to understand why it works
                    e.g how do we know it will form a cycle (reach start again) and why next(start++) is not yet moved
             */

            int curr = start;
            int prev = nums[curr];
            do{
                curr = (curr+k)%nums.length;
                int tmp = nums[curr];
                nums[curr] = prev;
                prev = tmp;

                count++;
            }while(curr!=start);
        }
    }
}
