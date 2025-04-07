package com.practice;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PacificAtlantic {
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
        final int w = heights.length, l = heights[0].length;
        int[][] directions = new int[][]{ {-1,0},{+1,0},{0,-1},{0,+1}};
        BiFunction<Integer,Integer,Integer> hf = (i,j) -> i*l+j;
        Set<Integer> p = new HashSet<>(), a = new HashSet<>();
        for(int i=0;i<w;i++){
            dfs(i,0,l,w,p,heights,directions,hf);
            dfs(i,l-1,l,w,a,heights,directions,hf);
        }

        for(int j=0;j<l;j++){
            dfs(0,j,l,w,p,heights,directions,hf);
            dfs(w-1,j,l,w,a,heights,directions,hf);
        }

        //intersect
        Function<Integer,List<Integer>> decode = (k) -> Arrays.asList(k/l, k%l);
        p.retainAll(a);
        return p.stream().map(decode).collect(Collectors.toList());
    }

    private void dfs(int i, int j , int l, int w,
                   Set<Integer> visit, int[][] heights,
                   int[][] directions, final BiFunction<Integer,Integer,Integer> hf){
        //base case
        if(visit.contains(hf.apply(i,j))){
            return;
        }

        visit.add(hf.apply(i,j));
        for(int[] direction:directions){
            int x = i + direction[0];
            int y = j + direction[1];
            if(x>=0 && x <w && y>=0 && y<l){
                if(heights[i][j]<=heights[x][y]){
                    dfs(x,y,l,w, visit,heights, directions, hf);
                }
            }
        }
    }
}
