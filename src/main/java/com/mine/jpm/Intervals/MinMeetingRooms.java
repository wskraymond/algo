package com.mine.jpm.Intervals;

import java.util.Arrays;
import java.util.Comparator;

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

        int max=1;
        int minEnd=intervals[0][1];
        int tmp=1;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i-1][1]>intervals[i][0]){
                tmp++;
            } else {
                max = Math.max(max, tmp);
                tmp = 1;
            }
        }

        max = Math.max(max, tmp);

        return max;
    }
}
