package com.mine.dp.fibonacci.MaxAlternatingSum;

public class MaxAlternatingSum {
    /**
     * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.
     *
     *     For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
     *
     * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
     *
     * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,2,5,3]
     * Output: 7
     * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
     *
     * Example 2:
     *
     * Input: nums = [5,6,7,8]
     * Output: 8
     * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
     *
     * Example 3:
     *
     * Input: nums = [6,2,1,2,4,5]
     * Output: 10
     * Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.
     *
     *
     *
     * Constraints:
     *
     *     1 <= nums.length <= 105
     *     1 <= nums[i] <= 105
     *
     * @param nums
     * @return
     */
    public long maxAlternatingSum(int[] nums) {
        /*
        Hints:
            1. Is only tracking a single sum enough to solve the problem?
            2. How does tracking an odd sum and an even sum reduce the number of states?
            3. max possible sum = 10^5/2 x 10^5 > Integer.MAX
            4. max possible number = 10^5 < Integer.MAX
         */
        /*
            1. subproblem - suffix[i:]
                f(i,0) => first number in the max-sum sub-sequence is even index
                f(i,1) => first number in the max-sum sub-sequence is odd index

            2. guessing to relate recurrence
               f(i, 0) = max{f(i+1, 1) + nums[i], f(i+1, 0)}
               f(i, 1) = max{f(i+1, 0) - nums[i], f(i+1, 1)}

            3. goal: f(0, 0)
            5. base case : f(n,0)=f(n,1)=0
            6. top. order:
                i: n-1....0
                    odd/even
            7. space complexity: O(2)=O(1)
            8. time complexity: O(2n)=O(n)
         */
        int n = nums.length;
        long odd=0, even=0;
        for(int i=n-1;i>=0;i--){
            long tmpEven = even;
            even = Math.max(odd+nums[i], even);
            odd = Math.max(tmpEven-nums[i], odd);
        }
        return even;
    }
}
