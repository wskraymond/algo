package com.mine.dp.minpathsum;

import java.util.Arrays;

public class MinPathSum_2D {
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
        int[][] dp = new int[rowSize][colSize];

/*        for(int i=0;i<rowSize;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }*/
        dp[0][0]=grid[0][0];
        for(int i=0; i<rowSize; i++){
            for(int j=0; j<colSize; j++){
                if(i==0 && j==0){
                    continue;
                }

                dp[i][j] = Math.min((i-1>=0) ? dp[i-1][j] : Integer.MAX_VALUE, (j-1>=0) ? dp[i][j-1] : Integer.MAX_VALUE) + grid[i][j];
            }
        }

        return dp[rowSize-1][colSize-1];
    }
}
