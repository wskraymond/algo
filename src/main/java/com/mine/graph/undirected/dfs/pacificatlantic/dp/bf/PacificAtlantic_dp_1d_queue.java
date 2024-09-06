package com.mine.graph.undirected.dfs.pacificatlantic.dp.bf;

import java.util.*;
import java.util.stream.IntStream;

public class PacificAtlantic_dp_1d_queue {
    /**
     * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
     *
     * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
     *
     * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
     *
     * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
     *
     *
     *
     * Example 1:
     *
     * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
     * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
     * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
     * [0,4]: [0,4] -> Pacific Ocean
     *        [0,4] -> Atlantic Ocean
     * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
     *        [1,3] -> [1,4] -> Atlantic Ocean
     * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
     *        [1,4] -> Atlantic Ocean
     * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
     *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
     * [3,0]: [3,0] -> Pacific Ocean
     *        [3,0] -> [4,0] -> Atlantic Ocean
     * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
     *        [3,1] -> [4,1] -> Atlantic Ocean
     * [4,0]: [4,0] -> Pacific Ocean
     *        [4,0] -> Atlantic Ocean
     * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
     *
     * Example 2:
     *
     * Input: heights = [[1]]
     * Output: [[0,0]]
     * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
     *
     *
     *
     * Constraints:
     *
     *     m == heights.length
     *     n == heights[r].length
     *     1 <= m, n <= 200
     *     0 <= heights[r][c] <= 105
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        /*
            flow from cell (ri, ci) to "both" the Pacific and Atlantic oceans
            (NOT either of oceans, but Both)
         */
        if(heights.length==0 || heights[0].length==0){
            return Collections.emptyList();
        }

        /*
            value = 2 bits = AP for A=Atlantic , P=Pacific
            Approach:
                At Most W*L-1 iteration to find max value for all nodes in a positive weighted graph without a negative cycle
            sub-problem:
                f(i,j,k) = f(i,j,k-1)
                            | f(i-1,j,k-1)
                            | f(i,j-1,k-1)
                            | f(i+1,j,k-1)
                            | f(i,j+1,k-1) if heights[i][j] >= neighbor's heights for (i+/-1, j+/-1)
                                                And 0 <= i+/-1 <= W && 0 <= j+/-1 <= L
            base case:
                f(W-1,j,0) |=10
                f(i,L-1,0) |=10
                f(0,j,0)   |=01
                f(i,0,0)   |=01
                f(i,j,0)   |=00

            Goal:
                f(i,j,W*L-1) for all node(i,j)
         */

        int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0},{-1,0}};
        final int W = heights.length, L = heights[0].length;

        final int A = 1<<1, P = 1<<0, BOTH = A | P;
        final int[][] dp = new int[W][L];

        boolean[][] inQ = new boolean[W][L];
        Queue<int[]> q = new LinkedList<>();
        IntStream.range(0,W).forEach(i->{  //dp variable needs to be final or effectively final in lambda
            dp[i][L-1] |= A;
            dp[i][0] |= P;

            q.add(new int[]{i,L-1});
            q.add(new int[]{i,0});

            inQ[i][L-1] = true;
            inQ[i][0] = true;
        });
        IntStream.range(0,L).forEach(j->{
            dp[W-1][j] |= A;
            dp[0][j] |= P;

            q.add(new int[]{W-1,j});
            q.add(new int[]{0,j});

            inQ[W-1][j] = true;
            inQ[0][j] = true;
        });


        List<List<Integer>> result = new LinkedList<>();
        while(!q.isEmpty()) { //O(M)
            int[] to = q.poll();
            final int i = to[0];
            final int j = to[1];
            for(int[] dir_from:directions){
                int x = i + dir_from[0];
                int y = j + dir_from[1];

                if(x<0 || x>=W || y<0 || y>=L || heights[i][j] > heights[x][y] || (dp[x][y] | dp[i][j]) == dp[x][y]){
                    continue;
                }

                if(!inQ[x][y]){
                    q.add(new int[]{x,y});
                    inQ[x][y] = true;
                }
                dp[x][y] |= dp[i][j];
            }

            inQ[i][j]=false;
        }   //Total = O(M*E)

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < L; j++) {
                if(dp[i][j]==BOTH){ //Goal: f(i,j,W*L-1)
                    result.add(Arrays.asList(i,j));
                }
            }
        }   //O(M)

        return result; //Total: O(M*E) + O(M)  = O(M*M*4 + M) = O(M^2)
    }
}
