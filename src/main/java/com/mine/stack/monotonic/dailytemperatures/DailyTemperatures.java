package com.mine.stack.monotonic.dailytemperatures;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    /**
     * https://leetcode.com/problems/daily-temperatures/
     *
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
     *
     *
     *
     * Example 1:
     *
     * Input: temperatures = [73,74,75,71,69,72,76,73]
     * Output: [1,1,4,2,1,1,0,0]
     * Example 2:
     *
     * Input: temperatures = [30,40,50,60]
     * Output: [1,1,1,0]
     * Example 3:
     *
     * Input: temperatures = [30,60,90]
     * Output: [1,1,0]
     *
     *
     * Constraints:
     *
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        /*
            return the distance in days inclusively
         */
        if(temperatures.length==0){
            return new int[0];
        }
        int[] ans = new int[temperatures.length];
        Deque<Integer> ascStack = new ArrayDeque<>(temperatures.length);
        for(int i=temperatures.length-1;i>=0;i--){
            int currVal = temperatures[i];
            while(!ascStack.isEmpty()
                    && currVal>=temperatures[ascStack.peek()]){
                ascStack.pop();
            }

            if(!ascStack.isEmpty()){//ans array defaults zero
                ans[i]=ascStack.peek()-i;
            }
            ascStack.push(i);
        }

        return ans;
    }
}
