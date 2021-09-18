package com.mine.jpm.permutation;

public class NextPermutation {
    /**
     * Implement next permutation,
     * which rearranges numbers into the lexicographically next greater permutation
     * of numbers.
     *
     * If such an arrangement is not possible,
     * it must rearrange it as the lowest possible order
     * (i.e., sorted in ascending order).
     *
     * The replacement must be in place and use only constant extra memory.
     *
     * Brute Force Thinking
     *
     * In this approach, we find out every possible permutation of list formed by the elements of the given array and find out the permutation which is just larger than the given one. But this one will be a very naive approach, since it requires us to find out every possible permutation which will take really long time and the implementation is complex. Thus, this approach is not acceptable at all. Hence, we move on directly to the correct approach.
     *
     * Complexity Analysis
     *
     *     Time complexity : O(n!). Total possible permutations is n!n!n!.
     *     Space complexity : O(n). Since an array will be used to store the permutations.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [1,3,2]
     *
     * Example 2:
     *
     * Input: nums = [3,2,1]
     * Output: [1,2,3]
     *
     * Example 3:
     *
     * Input: nums = [1,1,5]
     * Output: [1,5,1]
     *
     * Example 4:
     *
     * Input: nums = [1]
     * Output: [1]
     */
    public void nextPermutation(int[] nums) {

    }
}
