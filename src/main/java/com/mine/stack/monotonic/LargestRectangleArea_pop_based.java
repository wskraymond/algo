package com.mine.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.ToIntBiFunction;

public class LargestRectangleArea_pop_based {
    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     *
     * Example 1:
     *
     *
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * Example 2:
     *
     *
     * Input: heights = [2,4]
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 105
     * 0 <= heights[i] <= 104
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if(heights.length==0){
            return 0;
        }
        int max = 0, n = heights.length;
        Deque<Integer> descStack = new ArrayDeque<>(n);
        ToIntBiFunction<Integer, Integer> widthFunc = (l, r) -> r - l - 1;
        for(int i=0;i<n;i++){
            int nextRightVal = heights[i];
            while(!descStack.isEmpty()
                    && nextRightVal<heights[descStack.peek()]){
                int currHeight = heights[descStack.pop()];
                int indexOfNextLeft = descStack.isEmpty() ? -1 : descStack.peek();
                int indexOfNextRight = i;
                int area = widthFunc.applyAsInt(indexOfNextLeft, indexOfNextRight)*currHeight;
                max = Math.max(max, area);
            }
            descStack.push(i);
        }

        while(!descStack.isEmpty()){
            int currHeight = heights[descStack.pop()];
            int indexOfNextLeft = descStack.isEmpty() ? -1 : descStack.peek();
            int indexOfNextRight = n;
            int area = widthFunc.applyAsInt(indexOfNextLeft, indexOfNextRight)*currHeight;
            max = Math.max(max, area);
        }
        return max;
    }
}
