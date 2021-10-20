package com.mine.jpm.Intervals;

import java.util.*;

public class MergeInterval {
    /**
     * Given an array of intervals where intervals[i] = [starti, endi],
     * merge all overlapping intervals,
     * and return an array of the non-overlapping intervals
     * that cover all the intervals in the input.
     *
     * Example 1:
     *
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     *
     * Example 2:
     *
     * Input: intervals = [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     *
     * Constraints:
     *
     *     1 <= intervals.length <= 104
     *     intervals[i].length == 2
     *     0 <= starti <= endi <= 104
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
//        List<int[]> result = new ArrayList<>(intervals.length);
        //instead of using Array or ArrayList
        //frequent add() and getLast() without element shift
        LinkedList<int[]> result = new LinkedList<>();

        Arrays.sort(intervals, Comparator.comparingInt(i->i[0]));

        for(int i=0; i<intervals.length; i++){
            int[] top = result.isEmpty() ? null : result.getLast();
            if(top==null
                || intervals[i][0] > top[1]){
                result.add(Arrays.copyOf(intervals[i], 2));
            } else if(intervals[i][1] > top[1]){
                top[1] = intervals[i][1]; //reset
            } //else the top is the longest one
        }

        /**
         *
         Space complexity : O(logN) (or O(n))

         If we can sort intervals in place,
         we do not need more than constant additional space,
         although the sorting itself takes O(logn)O(\log n)O(logn) space.
         Otherwise, we must allocate linear space to store a copy of intervals and sort that.

         */
        return result.toArray(new int[result.size()][2]);
    }
}
