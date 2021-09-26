package com.mine.dp.minpathsum;

import java.util.Arrays;

public class MinPathSum_1D_inline {
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
        int[] dp = new int[colSize];

        for(int i=0; i<rowSize; i++){ //rowsize
            for(int j=0; j<colSize; j++){ //colsize
                if(i==0&&j==0){//top-left corner
                    dp[j]=grid[i][j];
                } else if(i==0&&j>0){ //top row
                    dp[j]=dp[j-1] + grid[i][j];
                } else if(i>0 && j==0){ //first col
                    dp[j]=dp[j] + grid[i][j]; //dp[j] is in time i-1, it means last processed row
                } else if(i>0 && j>0){ //in the middle (non-edge cell)
                    dp[j]=Math.min(dp[j], dp[j-1]) + grid[i][j];
                }
            }
        }

        return dp[colSize-1]; //O(rowSize * colSize)
    }
}
