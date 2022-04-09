package com.mine.cyclicsort.missingnumber;

import java.util.stream.IntStream;

public class FirstMissingPositive {
    /**
     * https://leetcode.com/problems/first-missing-positive/
     *
     * Given an unsorted integer array nums,
     * return the smallest missing positive integer.
     *
     * You must implement an algorithm that runs in O(n) time and uses constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,0]
     * Output: 3
     *
     * Example 2:
     *
     * Input: nums = [3,4,-1,1]
     * Output: 2
     *
     * Example 3:
     *
     * Input: nums = [7,8,9,11,12]
     * Output: 1
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 5 * 105
     *     -231 <= nums[i] <= 231 - 1
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        /*
        1. The number zero is neither positive nor negative. Positive and negative numbers are sometimes called signed numbers
        2. We don't care about duplicates or non-positive integers
                => only swap number in the range [1, n]
        3. Proof: we got n numbers ,
                and each number which is out of the range [1, n] will make one missing number ranged [1, n]
                becos this number occupied at a position.
                Thus, if we only swap number in the range [1, n] to its correct position,
                And then traverse it from index 0 to index n-1, then we just return the first mismatched number

        4. What if every index position contain the correct number ranged [1, n],
                then now, the smallest should be n+1......
         */
        int n = nums.length;
        int i = 0;
        while(i<n){
            int j = nums[i] -1;
            if(j>=0 && j<n && j+1!=nums[j]){
                nums[i] = nums[j];
                nums[j] = j+1;
            } else {
                i++;
            }
        }

        return IntStream.range(0, n).filter(e->e+1!=nums[e]).map(e->e+1).findFirst().orElse(n+1);
    }
}
