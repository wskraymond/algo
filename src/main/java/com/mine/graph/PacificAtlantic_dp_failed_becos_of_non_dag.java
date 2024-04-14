package com.mine.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PacificAtlantic_dp_failed_becos_of_non_dag {
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

        int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0},{-1,0}};
        final int l = heights.length, w = heights[0].length;
        int[][] memo = new int[l][w];
        List<List<Integer>> result = new LinkedList<>();
        for(int i=0;i<l;i++){
            for(int j=0;j<w;j++){
                if(memo[i][j]==0){
                    dfs(i,j, l, w , memo, heights, directions, result);
                }
            }
        }
        return result;
    }

    public int dfs(int i, int j , int l, int w,
                   int[][] memo, int[][] heights,
                   int[][] directions, List<List<Integer>> result){
        if(memo[i][j]!=0){
            return memo[i][j];
        }

        //bit = VPA , V for visit , P for pacific , A for atlantic
        int bitSet = 1<<2;
        if(i==0 || j==0){
            bitSet |= 1<<1;
        }

        if(i==l-1 || j==w-1){
            bitSet |= 1<<0;
        }

        for(int[] direction:directions){
            if(bitSet==7){
                break;
            }
            int nextR = i + direction[0];
            int nextC = j + direction[1];
            if(nextR>=0 && nextR<l
                && nextC>=0 && nextC<w
                && heights[i][j]>=heights[nextR][nextC]){
                bitSet |=dfs(nextR, nextC, w, l , memo, heights, directions, result);
            }
        }

        memo[i][j]=bitSet;
        if(bitSet==7){
            result.add(Arrays.asList(i,j));
        }
        return bitSet;
    }
}
