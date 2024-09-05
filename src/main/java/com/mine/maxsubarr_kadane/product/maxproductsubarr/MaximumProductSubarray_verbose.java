package com.mine.maxsubarr_kadane.product.maxproductsubarr;

import java.util.stream.Stream;

public class MaximumProductSubarray_verbose {
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
            4) multiply one positive val to product , it will increase positive product but decrease negative product
            Thus, we have to maintain 2 contiguous subarr product to update the max result.
         */
        /*
            Two variables of product ending at index i
                1) prev_neg : negative product ending at index i
                        which implies the odd number of negative integer in this subarray
                2) prev_p : positive product ending at index i
                        which implies the even number (including none) of negative integer in this subarray

            result: max of subarray product

            if val == 0:
              reset prev_neg and prev_p to empty
              update result by comparing with zero
            else if val > 0
              update prev_p by Max{ prev_p * val, val }
              update prev_neg by prev_neg * val
              update result by comparing with both prev
            else if val < 0
              update prev_p by prev_neg * val
              update prev_neg by Max{ prev_p * val, val}
              update result by comparing with both prev
         */
        final int n = nums.length;
        Integer prev_p=null, prev_neg=null;
        Integer max = null;
        for(int i=0;i<n;i++){
            int val = nums[i];
            if(val==0){
                max = max!=null ? Math.max(max, 0) : 0;
                prev_p=prev_neg=null;
            } else if(val>0){
                Integer p = prev_p != null ? prev_p * val : val;
                Integer neg = prev_neg!=null ? prev_neg * val : null;
                prev_p = p;
                prev_neg = neg;
                max = Stream.of(max, prev_neg, prev_p).filter(v->v!=null).mapToInt(Integer::intValue).max().getAsInt();
            } else if(val < 0){
                Integer p  = prev_neg!=null ? prev_neg*val : null;
                Integer neg = prev_p != null ? prev_p * val : val;
                prev_p = p;
                prev_neg = neg;
                max = Stream.of(max, prev_neg, prev_p).filter(v->v!=null).mapToInt(Integer::intValue).max().getAsInt();
            }
        }

        return max;
    }
}
