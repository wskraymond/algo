package com.mine.prefixsum;

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
        Map<Integer,Integer> m = new HashMap<>();
        m.put(0, 1);
        int total = 0;
        int sum = 0;
        for(int i=0;i< nums.length; i++){
            sum +=nums[i];
            int x = sum - k;
            if(m.containsKey(x)){
                total+=m.get(x);
            }
            m.merge(sum, 1, Integer::sum);
        }

        return total;
    }
}
