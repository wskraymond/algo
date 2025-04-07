package com.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuSolver {
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
        Deque<Integer> emptyCells = new ArrayDeque<>(n*n);
        boolean[][][] digits = new boolean[3][n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='.'){
                    emptyCells.push(i*n + j);
                } else {
                    int k = i / 3 * 3 + j / 3;
                    int x = board[i][j] - '1';
                    digits[0][i][x]=true;
                    digits[1][j][x]=true;
                    digits[2][k][x]=true;
                }
            }
        }

        dfs(emptyCells, n, board, digits);
    }

    public boolean dfs(Deque<Integer> emptyCells,final int n,
                       char[][] board, boolean[][][] digits){
        if(emptyCells.isEmpty()){
            return true;
        }

        int e = emptyCells.poll();
        int i = e/n, j = e%n;
        int k = i/3*3+j/3;
        for(int x=0;x<n;x++){
            if(digits[0][i][x]
                || digits[1][j][x]
                || digits[2][k][x]){
                continue;
            }

            digits[0][i][x]=true;
            digits[1][j][x]=true;
            digits[2][k][x]=true;
            board[i][j] = (char)('1'+x);

            if(dfs(emptyCells, n, board, digits)){
                return true;
            }

            digits[0][i][x]=false;
            digits[1][j][x]=false;
            digits[2][k][x]=false;
            board[i][j] = '.';
        }

        emptyCells.offerFirst(e);
        return false;
    }
}
