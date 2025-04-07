package com.practice;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlapIntervals {
    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi],
     * return the minimum number of intervals you need
     * to remove to make the rest of the intervals non-overlapping.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
     * Example 2:
     *
     * Input: intervals = [[1,2],[1,2],[1,2]]
     * Output: 2
     * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
     * Example 3:
     *
     * Input: intervals = [[1,2],[2,3]]
     * Output: 0
     * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     *
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 105
     * intervals[i].length == 2
     * -5 * 104 <= starti < endi <= 5 * 104
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        /*
            0.sort by start_t
            1. greedy to remove overlapping interval selected by min end_t
                (this is like using minHeap ordered by end_t in min. meeting room )
         */
        if(intervals.length<=1){
            return 0;
        }

        int count=0;
        Arrays.sort(intervals, Comparator.comparingInt(interval->interval[0]));
        int end_t = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(end_t>intervals[i][0]){
                end_t=Math.min(intervals[i][1], end_t);
                count++;
            }else{
                end_t=intervals[i][1];
            }
        }
        return count;
    }
}
