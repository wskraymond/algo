package com.mine.cyclicsort.missingnumber;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MissingNumber {
    /**
     * Given an array nums containing n distinct numbers in the range [0, n],
     * return the only number in the range that is missing from the array.
     *
     * Example 1:
     *
     * Input: nums = [3,0,1]
     * Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
     *
     * Example 2:
     *
     * Input: nums = [0,1]
     * Output: 2
     * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
     *
     * Example 3:
     *
     * Input: nums = [9,6,4,2,3,5,7,0,1]
     * Output: 8
     * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
     *
     *
     *
     * Constraints:
     *
     *     n == nums.length
     *     1 <= n <= 104
     *     0 <= nums[i] <= n
     *     All the numbers of nums are unique.
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        /*
        Follow up: Could you implement a solution
        using only O(1) extra space complexity and O(n) runtime complexity?
         */
        int n = nums.length;
        int i = 0;
        while(i<n){ //O(n)
            int j = nums[i];

            if(j<n && j!=nums[j]){
                nums[i] = nums[j];
                nums[j] = j;
            } else {
                i++;
            }
        }

        //NO extra storage for range()
        //Returns a sequential ordered IntStream from startInclusive (inclusive)
        //  to endExclusive (exclusive) by an incremental step of 1.
        return IntStream.range(0, n).filter(e->e!=nums[e]).findAny().orElse(n);
    }
}
