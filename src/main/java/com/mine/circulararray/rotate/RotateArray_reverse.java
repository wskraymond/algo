package com.mine.circulararray.rotate;

public class RotateArray_reverse {
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
        //reverse entire array
        reverse(nums, 0, nums.length-1);
        //reverse k
        reverse(nums, 0, k-1);
        //reverse n-k
        reverse(nums, k, nums.length-1);
    }

    private void reverse(int[] nums, int start, int end){
        for(int i=start,j=end;i<j;i++, j--){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
