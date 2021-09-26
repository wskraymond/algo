package com.mine.dp.minpathsum;

import java.util.Arrays;

public class MinPathSum_1D_clone {
    /**
     * Given a m x n grid filled with non-negative numbers,
     * find a path from top left to bottom right,
     * which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     * Example 1:
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
     *
     * Example 2:
     *
     * Input: grid = [[1,2,3],[4,5,6]]
     * Output: 12
     *
     * Constraints:
     *
     *     m == grid.length
     *     n == grid[i].length
     *     1 <= m, n <= 200
     *     0 <= grid[i][j] <= 100
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;
        int[] prev = new int[colSize];
        int[] curr = new int[colSize];

        Arrays.fill(prev, -1);
        for(int i=0; i<rowSize; i++){ //rowsize
            curr[0]=(prev[0] >= 0) ? prev[0] + grid[i][0] : grid[i][0] ;
            for(int j=1; j<colSize; j++){ //colsize
                curr[j] = Math.min(prev[j] >=0 ? prev[j] :Integer.MAX_VALUE,
                            curr[j-1]) + grid[i][j];
            }

            //https://www.softwaretestinghelp.com/java-copy-array/#Using_Objectclone
            prev = curr.clone(); //colsize
        }

        return curr[colSize-1]; //O(rowSize * (2*colSize))
    }
}
