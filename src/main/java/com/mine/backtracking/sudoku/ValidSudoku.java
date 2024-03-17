package com.mine.backtracking.sudoku;

import java.util.*;

public class ValidSudoku {
    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     *     Each row must contain the digits 1-9 without repetition.
     *     Each column must contain the digits 1-9 without repetition.
     *     Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     *
     * Note:
     *
     *     A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     *     Only the filled cells need to be validated according to the mentioned rules.
     *
     *
     *
     * Example 1:
     *
     * Input: board =
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * Output: true
     *
     * Example 2:
     *
     * Input: board =
     * [["8","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * Output: false
     * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
     *
     *
     *
     * Constraints:
     *
     *     board.length == 9
     *     board[i].length == 9
     *     board[i][j] is a digit 1-9 or '.'.
     *
     * @param board
     * @returnH
     */
    public boolean isValidSudoku(char[][] board) {
        final int n = 9;
        final int s = (int)(n/0.75)+1;
        Map<Integer,Set<Character>> row = new HashMap<>(s);
        Map<Integer,Set<Character>> col = new HashMap<>(s);
        Map<Integer,Set<Character>> subSq = new HashMap<>(s);

        for(int i=0;i<n;i++){
            row.putIfAbsent(i, new HashSet<>(s));
            for(int j=0;j<n;j++){
                int k = i/3*3+j/3;
                col.putIfAbsent(j, new HashSet<>(s));
                subSq.putIfAbsent(k, new HashSet<>(s));
                char val = board[i][j];
                if(val!='.'){
                    if(!( row.get(i).add(val)
                        && col.get(j).add(val)
                        && subSq.get(k).add(val))
                    ){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
