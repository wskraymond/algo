package com.mine.greedy.jumpgame.jumpgame1;

public class JumpGame1_dp {
    /**
     * You are given an integer array nums. You are initially positioned at the array's first index,
     * and each element in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 105
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        /*
            sub-problem:
                suffix[i:]
            Recurrence relation:
                f(i) = { found ||=f(j)
                            | for j = i+1...i+nums[i]
                            | initialized found=false
                        }
            Base Case:
                f(n-1) = true
            Goal:
                f(0)
            Time Complexity:
                #subproblem x #guesse =O(nxn)=O(n^2)
         */
        final int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1]=true;
        for(int i=n-2;i>=0;i--){
            for(int j=i+1;j<=i+nums[i] && j<n;j++){
                dp[i]|=dp[j];
            }
        }

        return dp[0];
    }
}
