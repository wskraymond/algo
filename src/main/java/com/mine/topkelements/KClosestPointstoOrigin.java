package com.mine.topkelements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointstoOrigin {
    /**
     * Given an array of points where points[i] = [xi, yi]
     * represents a point on the X-Y plane and an integer k,
     * return the k closest points to the origin (0, 0).
     *
     * The distance between two points on the X-Y plane is
     * the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
     *
     * You may return the answer in any order.
     * The answer is guaranteed to be unique (except for the order that it is in).
     *
     * Example 1:
     * Input: points = [[1,3],[-2,2]], k = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
     *
     * Example 2:
     *
     * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
     * Output: [[3,3],[-2,4]]
     * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
     *
     * Constraints:
     *     1 <= k <= points.length <= 104
     *     -104 < xi, yi < 104
     *
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest(int[][] points, int k) {
        final int[] origin = new int[]{0,0};
        //negate the distance for descending order
        Queue<double[]> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(p->-p[2]));
        for(int i=0;i<points.length;i++){
            int[] p = points[i];
            double dis = distance(p, origin);
            if(maxHeap.size()==k){
                double max = maxHeap.peek()[2];
                if(Double.compare(dis, max)<0){
                    maxHeap.poll();
                    maxHeap.add(new double[]{p[0], p[1], dis});
                }
            } else {
                maxHeap.add(new double[]{p[0], p[1], dis});
            }
        }

        return maxHeap.stream().map(p->new int[]{(int) p[0], (int) p[1]}).toArray(int[][]::new);
    }

    public double distance(int[] p1, int[] p2){
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}
