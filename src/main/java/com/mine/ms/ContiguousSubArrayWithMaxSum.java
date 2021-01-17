package com.mine.ms;


/**
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class ContiguousSubArrayWithMaxSum {

    public int play(int[] arr){
        int tmp = 0;
        int max = arr[0];
        for(int i=1;i<arr.length;i++){
            if(tmp + arr[i] > 0) {
                tmp += arr[i];
                if (tmp > max){
                    max = tmp;
                }
            } else {
                tmp =0;
                if(arr[i] >max)
                    max = arr[i];
            }
        }

        return max;
    }
}
