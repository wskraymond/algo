package com.mine.cyclicsort.missingnumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllNumbersDisappeared {
    /**
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     *Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [5,6]
     *
     * Example 2:
     *
     * Input: nums = [1,1]
     * Output: [2]
     *
     *
     *
     * Constraints:
     *
     *     n == nums.length
     *     1 <= n <= 105
     *     1 <= nums[i] <= n
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*
        Follow up:
        1. Could you do it without extra space and in O(n) runtime ?

        2. You may assume the returned list does not count as extra space.
         */
        int n = nums.length;
        int i = 0;
        while(i<n){
            int j = nums[i]-1;
            if(j+1!=nums[j]){
                nums[i] = nums[j];
                nums[j] = j+1;
            } else {
                i++;
            }
        }

        return IntStream.range(0, n)
                .filter(e->e+1!=nums[e])
                .map(e->e+1)
                .boxed()
                .collect(Collectors.toList());
    }
}
