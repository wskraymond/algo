package com.mine.linear.longestconsecutivesequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence_unionfind_n {
    /**
     * Given an unsorted array of integers nums,
     * return the length of the longest consecutive elements sequence.
     *
     * You must write an algorithm that runs in O(n) time.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     * Example 2:
     *
     * Input: nums = [0,3,7,2,5,8,4,6,0,1]
     * Output: 9
     *
     *
     * Constraints:
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        /**
         * "You must write an algorithm that runs in O(n) time."
         */
        if(nums.length==0){
            return 0;
        }

        Set<Integer> s= new HashSet<>();
        for(int num:nums){
            s.add(num);
        }

        int max=1;
        for(int i=0;i<nums.length;i++){ //root iterating
            if(!s.contains(nums[i]-1)){ //IsRoot ?
                int tmp=1;
                int currNum=nums[i]; //root
                while(s.contains(currNum+1)){ //set/sequence traversal
                    tmp++;
                    currNum++;
                }
                max = Math.max(max, tmp);
            }
        }

        return max;
    }
}
