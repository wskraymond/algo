package com.mine.backtracking;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     *
     *
     *
     * Example 1:
     *
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * Output: true
     *
     * Example 2:
     *
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
     * Output: true
     *
     * Example 3:
     *
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * Output: false
     *
     *
     *
     * Constraints:
     *
     *     m == board.length
     *     n = board[i].length
     *     1 <= m, n <= 6
     *     1 <= word.length <= 15
     *     board and word consists of only lowercase and uppercase English letters.
     *
     *
     *
     * Follow up: Could you use search pruning to make your solution faster with a larger board?
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(word.charAt(0)!=board[i][j]){
                    continue;
                }

                visited[i][j]=true;
                if(backtrack(board,word,0,i,j,n,m,visited)){
                    return true;
                }
                visited[i][j]=false;
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, String word,
                             int cIndex,
                             int i, int j,
                             int n, int m,
                             boolean[][] visited){
        if(cIndex==word.length()-1){
            return true;
        }

        List<int[]> directions = new ArrayList<>();
        if(i-1 >=0){
            directions.add(new int[]{i-1,j});
        }
        if(i+1 <n){
            directions.add(new int[]{i+1,j});
        }
        if(j-1 >=0){
            directions.add(new int[]{i,j-1});
        }
        if(j+1 <m){
            directions.add(new int[]{i,j+1});
        }

        boolean result = false;
        for(int[] direction: directions){
            int x=direction[0], y=direction[1];
            if(visited[x][y]){//is visited
               continue;
            }

            if(word.charAt(cIndex+1)!=board[x][y]){ //pruning
                continue;
            }

            visited[x][y]=true;
            result = backtrack(board,word, cIndex+1, x,y,n,m, visited);
            visited[x][y]=false;
            if(result){
                break;
            }
        }

        return result;
    }
}
