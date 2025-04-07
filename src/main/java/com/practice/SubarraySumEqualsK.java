package com.practice;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    /**
     *
     * https://leetcode.com/problems/subarray-sum-equals-k/
     *
     * Given an array of integers nums and an integer k,
     * return the total number of continuous subarrays whose sum equals to k.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     * Example 2:
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 104
     * -1000 <= nums[i] <= 1000
     * -107 <= k <= 107
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        /*
            0. value ranges from negative to positive
            1. a - b = k
            2. iterate from left to go thru the calculation
            3. map{b, count} , m = {0:1}
            4. count+=count
         */
        int prefixSum=0, count=0;
        final int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        for(int i=0;i<n;i++){
            prefixSum+=nums[i];
            int b = prefixSum - k;
            count+=m.getOrDefault(b,0);

            m.merge(prefixSum, +1, Integer::sum);
        }
        return count;
    }
}
