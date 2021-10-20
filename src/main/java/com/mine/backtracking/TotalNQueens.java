package com.mine.backtracking;

import java.util.HashSet;
import java.util.Set;

public class TotalNQueens {
    /**
     * The n-queens puzzle is the problem of
     * placing n queens on an n x n chessboard
     * such that no two queens attack each other.
     *
     *
     * A queen - Q
     * d  c ad
     * r  Q  r
     * ad c  d
     *
     *
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     *
     * Example 1:
     * Input: n = 4
     * Output: 2
     * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
     *
     * Example 2:
     * Input: n = 1
     * Output: 1
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        return backtrack(0, n, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public int backtrack(int row, int size,
                         Set<Integer> cols, Set<Integer> diagonals,
                         Set<Integer> antiDiagonals){
        //base case
        if(row==size){ //the required number of candidates in a solution
            return 1;
        }

        //n x n = #no of candidates in total
        int result = 0;
        for(int i=0;i<size;i++){
            //state - attacking direction
            //row -> row
            //col -> i
            //diagonal -> row - col  //unique number move forward by rows
            //anti-diagonal -> row + col  //unique number move backward by rows
            int c = i;
            int d = row - i;
            int ad = row + i;

            //isValid - make sure that Queen won't attack each other
            if(cols.contains(c)
                || diagonals.contains(d)
                || antiDiagonals.contains(ad)){
                // at nth row,  there can be no final solution(leaf) which is valid
                continue;
            }

            //save state - place queen
            cols.add(c);
            diagonals.add(d);
            antiDiagonals.add(ad);

//            System.out.println(String.format("Queen: c:%d, r:%d", i, row));

            /*if(row==size-1){
                //debug
                System.out.println(String.format("final Queen: c:%d, r:%d", i, row));
                return 1; //final solution(leaf)
                //lead to wrong counting becos of no backtracking
                //how to deal with this ?
                //1 more node(N+1) (tree traversal) as the leaf(base case) as the final valid solution
                //which makes the code cleaner and easy to understand !!!!
            } else {*/
                result+=backtrack(row+1, size,
                        cols, diagonals,
                        antiDiagonals);
            /*}*/

            //backtrack - remove state(queen)
            cols.remove(c);
            diagonals.remove(d);
            antiDiagonals.remove(ad);
        }

        return result;
    }
}
