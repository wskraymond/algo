package com.mine.cyclicsort.finduplicates;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates_mine {
    /**
     * https://leetcode.com/problems/find-all-duplicates-in-an-array/
     *
     * Given an integer array nums of length n
     * where all the integers of nums are in the range [1, n]
     * and each integer appears once or twice,
     * return an array of all the integers that appears twice.
     *
     * You must write an algorithm that runs in O(n) time and uses only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [2,3]
     * Example 2:
     *
     * Input: nums = [1,1,2]
     * Output: [1]
     * Example 3:
     *
     * Input: nums = [1]
     * Output: []
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 105
     * 1 <= nums[i] <= n
     * Each element in nums appears once or twice.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        //O(n) -> each number will only be assigned( swap ) to its correct position once
        for(int i=1;i<=nums.length;i++){
            int toIndex = nums[i-1];

            //while condition serves both cases
            //case 1: toIndex-1 == i , then stop swap
            //case 2: toIndex-1 != i && nums[toIndex-1]==toIndex, then stop swap
            while(nums[toIndex-1]!=toIndex){
                //1. swap
                nums[i-1] = nums[toIndex-1];
                nums[toIndex-1] = toIndex;

                //2. get new value at current position after swap
                toIndex = nums[i-1];
            }
        }

        //O(n): filter out duplicate number
        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]!=i){
                result.add(nums[i-1]);
            }
        }

        return result;
    }
}
