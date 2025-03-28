package com.practice;

import java.util.stream.IntStream;

public class MaximumProductSubarray {
    /**
     * https://leetcode.com/problems/maximum-product-subarray/
     *
     * Given an integer array nums, find a
     * subarray
     *  that has the largest product, and return the product.
     *
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     *
     * Input: nums = [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 104
     * -10 <= nums[i] <= 10
     * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        /*  Intuitive
            1) odd number of neg val = negative product
            2) even number of neg val = positive product
            3) multiply one neg val to product , it will negate the sign of product
                -> swap Min and Max
            4) multiply one positive val to product , it will increase positive product but decrease negative product
                -> accumulate both Min and Max
            Thus, we have to maintain 2 contiguous subarr product to update the max result.
         */
        /*
            Two variables of product ending at index i
                1) Min : Min product ending at index i , which will contain last valid negative product or any single value
                2) Max : Max product ending at index i , which will contains last valid positive product or any single value

            To simplify the edge case handling, we compare 3 products, Min*val, Max*val and val
            However, we take Max and Min func to those 3 products, then update Min, Max and result.
         */


        return 0;
    }
}
