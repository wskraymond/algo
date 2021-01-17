package com.mine.ms;

public class LongestIncreSeq {
    /**
     * case 0
     * arr: 1 2
     * Max(2,1): T(-oo, 0) = Max(T(arr[0],1) + 1, T(-oo, 1))    // 1>-oo , include index 0 for [-oo->1] or skip index 0 for [-oo-> empty]
     *                     = Max(step 1 + 1, step 2)
     *                     = Max( 1 + 1, 1)
     * step 1: Max(1,0): T(arr[0],1) = Max(T(arr[1],2)+1, T(-oo, 2))   // 2>1 , include index 1 for [1->2] or skip index 1 for [-oo->empty]
     * step 2: Max(1,0)  : T(-oo, 1) = Max(T(arr[1],2) + 1,T(-oo, 2))  //2>-oo , include index 2(nothing) for [-oo->empty->2] or skip index 1 for [-oo->empty]
     * 0 : T(any, 2)                                         //2==size
     *
     * case 1
     * arr: 1,4,2,3,5,0,8,9
     * 1 -> 2 -> 3 -> 5       -> 8 -> 9
     * 1-> 4       -> 5     -> 8 -> 9
     *                    0-> 8 -> 9
     * ....etc
     *
     * case 2
     * arr: 1 10 11 2 3 4 12 5 6 7
     * 1-> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     * 1-> 10 -> 11 -> 12
     * 1-> 2 -> 3 -> 4 -> 12
     *....etc
     *
     * recursion function: big O (2^n) //exponential time
     *      T(arr,prev, i) = Max(step 1 , step 2)
     *          1) include i if arr[prev] < arr[i] : T(arr, arr[i], i+1) + 1 , if no -> 0
     *          2) skip i    : T(arr, prev, i+1)
     *
     *      base: if i==size , return 0
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS(nums,Integer.MIN_VALUE, 0);
    }

    public int lengthOfLIS(int[] nums, int prev, int i) {
        if(i==nums.length)
            return 0;

        int val1 = 0;
        if(nums[i]>prev)
            val1 = lengthOfLIS(nums, nums[i], i+1) + 1;

        int val2 = lengthOfLIS(nums, prev, i+1);

        return Math.max(val1, val2);
    }
}
