package com.mine.graph.undirected.bfs;

import java.util.*;

public class NumIslands {
    /**
     * Given an m x n 2D binary grid
     * which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     * Example 2:
     *
     * Input: grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * Output: 3
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is '0' or '1'.
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int nr = grid.length;
        int nc = grid[0].length;

        boolean[][] visited = new boolean[nr][nc];
        Set<Point> islands = new HashSet<>();
        for(int i=0;i<nr;i++){
            for(int j=0;j<nc;j++){
                if(grid[i][j]=='1'){
                    islands.add(new Point(i,j));
                }
            }
        }

        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        int result = 0;
        while(!islands.isEmpty()){
            Point p = islands.iterator().next();
            result++;
            Queue<Point> q = new LinkedList<>();
            q.add(p);
            while(!q.isEmpty()){
                Point land = q.poll();
                if(!visited[land.y][land.x]) {
                    visited[land.y][land.x] = true;
                    islands.remove(land);

                    for(int[] direction: directions){
                        int newX = land.x + direction[0];
                        int newY = land.y + direction[1];

                        if(newY >=0 && newY < nr
                                && newX >= 0 && newX < nc
                                && !visited[newY][newX]
                                && grid[newY][newX]=='1'){
                            q.add(new Point(newY, newX));
                        }
                    }
                }
            }
        }

        return result;
    }

    class Point{
        final int x;
        final int y;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
