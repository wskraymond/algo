package com.mine.backtracking.directions.mineffortpath;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinEffortPath_Dijkstra {
    /**
     * You are a hiker preparing for an upcoming hike.
     * You are given heights, a 2D array of size rows x columns,
     * where heights[row][col] represents the height of cell (row, col).
     *
     * You are situated in the top-left cell, (0, 0),
     * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
     * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
     *
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        /**
         * src = (0,0)
         * dst = (n-1,n-1)
         * vertex: (i,j)
         * path distance(not yet confirmed that it is minimum effort) from src to (i,j): minDiff[i][j]
         * Graph:
         *  - heights
         *  - edge: up, right, down , left
         *  - boundary check
         * Min Heap:
         *  - connected vertices{i, j, maxDiff(i,j)}
         *  - for greedy choice
         * Recursion Relations:
         *  - maxDiff(i,j) = max{ min{heap}, diff((x,y)->(i,j)) } | given vertex(x,y) of min{heap} is the locally optimal choice
         *  - And given that maxDiff(i,j) >= min{heap}  # increasing only
         * Visited And/Or Shortest Distance(sd)/:
         *  - 1) boolean[] visited
         *      - visited[i] = true
         *      - And whenever reaches its adjacent vertices
         *          - if(minDiff[i][j] > maxDiff(i,j))
         *              - minDiff[i][j] = maxDiff(i,j)
         *              - add the adjacent to the queue
         *  - 2) sd[i][j] = min{heap} | given vertex(i) of min{heap} is the global optimal value
         *      - if(sd[i][j]!=Integer.MAX_VALUE)
         * Our goal:
         *   minDiff[n-1][n-1] or sd[n-1][n-1]
         */
        int r = heights.length;
        int c = heights[0].length;
        int[][] minDiffs = new int[r][c];
        for(int [] minDiff : minDiffs) {
            Arrays.fill(minDiff, Integer.MAX_VALUE);
        }
        minDiffs[0][0] = 0;

        boolean[][] visited = new boolean[r][c];
        Queue<int[]> effortPathQ = new PriorityQueue<>(Comparator.comparingInt(v->v[2]));
        effortPathQ.add(new int[]{0,0,0}); //{i,j, maxEffortInPath}

        //up, right, down , left
        final int[][] directions = new int[][]{{-1,0}, {0,1}, {1, 0}, {0,-1}};
        while(!effortPathQ.isEmpty()){
            int[] vertex = effortPathQ.poll();
            int i=vertex[0];
            int j=vertex[1];
            int maxEffortInPath=vertex[2];

            if(visited[i][j]){
                continue;
            }
            visited[i][j]=true;

            if(i==r-1 && j==c-1){
                break;
            }

            for(int[] direction:directions){
                int i2 = i + direction[0];
                int j2 = j + direction[1];

                if(i2>=0 && i2<r && j2>=0 && j2 <c && !visited[i2][j2]){
                    int diff2 = Math.abs(heights[i][j] - heights[i2][j2]);
                    int maxEffortInPath2 = Math.max(maxEffortInPath, diff2);

                    if(minDiffs[i2][j2] > maxEffortInPath2){
                        minDiffs[i2][j2] = maxEffortInPath2;
                        effortPathQ.add(new int[]{i2,j2, maxEffortInPath2});
                    }
                }
            }
        }

        return minDiffs[r-1][c-1];
    }
}
