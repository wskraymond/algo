package com.mine.jpm.Intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinMeetingRooms {
    /**
     * Given an array of meeting time intervals intervals
     * where intervals[i] = [starti, endi],
     * return the minimum number of conference rooms required.
     *
     *
     * Example 1:
     *
     * Input: intervals = [[0,30],[5,10],[15,20]]
     * Output: 2
     *
     * Example 2:
     *
     * Input: intervals = [[7,10],[2,4]]
     * Output: 1
     *
     *
     * Constraints:
     *
     *     1 <= intervals.length <= 104
     *     0 <= starti < endi <= 106
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length<1){
            return 1;
        }

        Arrays.sort(intervals, Comparator.comparingInt(i->i[0]));

        Queue<Integer> minEndQ = new PriorityQueue<>();
        minEndQ.add(intervals[0][1]);
        for(int i=1;i<intervals.length;i++){
            int minEnd = minEndQ.peek();
            int start = intervals[i][0];
            int end = intervals[i][1];
            if(start<minEnd){
                minEndQ.add(end);
            } else {
                minEndQ.poll();
                minEndQ.add(end);
            }
        }

        return minEndQ.size();
    }
}
