package com.mine.graph.pacificatlantic.traverse;

import java.util.*;
import java.util.stream.IntStream;

public class PacificAtlantic_two_dfs_incorrect_hashkey {
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

        final int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0},{-1,0}};
        final int l = heights.length, w = heights[0].length;
        Set<Integer> p = new HashSet<>(), a = new HashSet<>();
        List<List<Integer>> result = new LinkedList<>();
        for(int i=0;i<l;i++){
            dfs(i,0, l,w,p,heights,directions);
            dfs(i,w-1, l,w,a,heights,directions);
        }

        for(int j=0;j<w;j++){
            dfs(0,j,l,w,p,heights,directions);
            dfs(l-1,j,l,w,a,heights,directions);
        }

        for(int i=0;i<l;i++){
            for(int j=0;j<w;j++){
                if(p.contains(i+j) && a.contains(i+j)){
                    result.add(Arrays.asList(i,j));
                }
            }
        }

        return result;
    }

    public void dfs(int i, int j , int l, int w,
                   Set<Integer> visit, int[][] heights,
                   int[][] directions){
        if(visit.contains(i+j)){
            return;
        }

        visit.add(i+j);
        for(int[] direction:directions){
            int next_i = i + direction[0];
            int next_j = j + direction[1];
            if(next_i >= 0 && next_i<l
                && next_j >= 0 && next_j<w
                && heights[i][j]<=heights[next_i][next_j]){
                dfs(next_i,next_j, l, w, visit, heights, directions);
            }
        }
    }
}
