package com.mine.backtracking.sudoku;

import java.util.stream.IntStream;

public class SudokuSolver_boolean {
    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     *     Each of the digits 1-9 must occur exactly once in each row.
     *     Each of the digits 1-9 must occur exactly once in each column.
     *     Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     *
     * The '.' character indicates empty cells.
     *
     *
     *
     * Example 1:
     *
     * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
     * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
     * Explanation: The input board is shown above and the only valid solution is shown below:
     *
     *
     *
     *
     * Constraints:
     *
     *     board.length == 9
     *     board[i].length == 9
     *     board[i][j] is a digit or '.'.
     *     It is guaranteed that the input board has only one solution.
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        /*
            It is guaranteed that the input board has only one solution.
         */
        final int n = board.length;
        boolean[][][] digits = new boolean[3][n][n];
        IntStream.range(0,n).forEach(i->IntStream.range(0,n).forEach(j->{
            if(board[i][j] !='.'){
                int x = board[i][j] - '1';
                int k = i/3*3+j/3;
                digits[0][i][x]=true;
                digits[1][j][x]=true;
                digits[2][k][x]=true;
            }
        }));

        dfs(0,0,n,board,digits);
    }

    public boolean dfs(int i, int j,int n,
                    char[][] board,boolean[][][] digits){
        if(i==n || j==n){//base case
            return true;
        }

        final int nextR = i+(j+1)/n, nextC=(j+1)%n;

        if(board[i][j]!='.'){
            return dfs(nextR, nextC, n, board, digits);
        }

        /*
            Follow up : what if binary operation
              Num index: 012345678
                    row: 001001000
              OR    col: 100101000
              OR    sub: 100101010
              And input: 001000000
                         001000000 => invalid
         */
        for(int x=0;x<n;x++){
            final int k = i/3*3+j/3;
            if(digits[0][i][x]
                || digits[1][j][x]
                || digits[2][k][x]){
                continue;
            }

            digits[0][i][x]=true;
            digits[1][j][x]=true;
            digits[2][k][x]=true;
            board[i][j]=(char) ('1'+x);

            boolean valid = dfs(nextR, nextC, n, board, digits);
            if(valid){
               return true;
            }

            //backtrack
            digits[0][i][x]=false;
            digits[1][j][x]=false;
            digits[2][k][x]=false;
            board[i][j]='.';
        }

        return false;
    }
}
