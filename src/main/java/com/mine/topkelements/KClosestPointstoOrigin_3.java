package com.mine.topkelements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointstoOrigin_3 {
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
        //negate the distance for descending order
        //heap array with initial size
        Queue<int[]> maxHeap = new PriorityQueue<>(k+1, Comparator.comparingInt(p-> -p[2]));
        for(int i=0;i<points.length;i++){ //O(n)
            int[] p = points[i];
            //avoid recompute the sqDist when adding a new node to heap tree
            int sqDist = sqDistFromOrigin(p);
            maxHeap.add(new int[]{p[0], p[1], sqDist}); //O(log(k))

            if(maxHeap.size() > k){
                maxHeap.poll(); //O(log(k))
            }
        }

        return maxHeap.stream().map(p->new int[]{p[0], p[1]}).toArray(int[][]::new); //O(k*log(k))
        //total time complexity: O(2*nlogk + k*logk) = O(nlogk)
    }

    public int sqDistFromOrigin(int[] p){
        return p[0]*p[0] + p[1]*p[1];
    }
}
