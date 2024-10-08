package com.mine.fastslowpointers;

public class FindtheDuplicateNumber_n_plus_one_integer {
    /**
     * https://leetcode.com/problems/find-the-duplicate-number/
     *
     * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
     *
     * There is only one repeated number in nums, return this repeated number.
     *
     * You must solve the problem without modifying the array nums and uses only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * Input: nums = [3,1,3,4,2]
     * Output: 3
     * Example 3:
     *
     * Input: nums = [1,1]
     * Output: 1
     * Example 4:
     *
     * Input: nums = [1,1,2]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= n <= 105
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * All the integers in nums appear only once except for precisely one integer which appears two or more times.
     *
     *
     * Follow up:
     *
     * How can we prove that at least one duplicate number must exist in nums?
     * Can you solve the problem in linear runtime complexity?
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int s=0, f=0;
        int p1=0, p2=-1;
        while(true){
            s=nums[s];
            f=nums[nums[f]];
            if(s==f){
                p2=s;
                break;
            }
        }

        if(p2>-1){
            while(p1!=p2){
                p1 = nums[p1];
                p2 = nums[p2];
            }
        }


        return p2;
    }
}
