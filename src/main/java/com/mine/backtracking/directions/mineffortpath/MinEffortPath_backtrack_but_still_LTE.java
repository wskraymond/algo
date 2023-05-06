package com.mine.backtracking.directions.mineffortpath;

import java.util.LinkedList;
import java.util.List;

public class MinEffortPath_backtrack_but_still_LTE {
    /**
     * From leetcode comment
     * One way to realize that it isn't dynamic programming
     * is to notice that the hiker can go in all four directions.
     * This means that a dp algorithm would need to look into subproblems
     * that haven't been solved yet.
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        /**
         * weight= w(k,v) = |val(k) - val(v)|
         * next cell(k) from the current: l=left , r=right, u=up, d=down
         * Recurrence Relation: s=topleft corner , v=bottemright corner
         *      f(s,v) = min{ max{f(s,l),w(l,v)},  max{f(s,r),w(r,v)}
         *               ,  max{f(s,u),w(u,v)}, max{f(s,d),w(d,v)} | for any valid move}
         *
         * Base Case: if k = s, then return w(s, s) = Integer.MIN
         *
         * init: f(s,k) = Integer.MAX
         * Candidates selection
         *      - for(cell k : {l,r, u,d})
         *          - isValid(k) = if current path contains k
         *              - add selected cell to path(Hashset)
         *          - move to next cell k
         *              - optimization (choose best choice) for f(s,k)
         *          - backtrack the option k from current path
         *
         * Return f(s,k)
         *
         */

        //prefix recursion
        int rn = heights.length;
        int cn = heights[0].length;
        boolean[][] path = new boolean[rn][cn];
        path[rn-1][cn-1] = true;
        return backtrack(rn-1, cn-1, path, heights, rn, cn, 0);
    }

    /*
    @Leetcode: "Attempt" to pass "Time Limit Exceeded" for input = [[4,3,4,10,5,5,9,2],[10,8,2,10,9,7,5,6],[5,8,10,10,10,7,4,2],[5,1,3,1,1,3,1,9],[6,4,10,6,10,9,4,6]]
     Tricks: (but still TLE....)
         1. A global variable is required....
         2. input parameter - currMaxEffort is required to forward the maxEffort in the current path to the end(here is src).

     Reasons:
        - To make the algorithm more efficient, once we find any path from source to destination, we track the maximum absolute difference of all adjacent cells in that path in a variable maxSoFar\text{maxSoFar}maxSoFar. With this, we can avoid going into other paths in the future where effort is greater than or equal to maxSoFar\text{maxSoFar}maxSoFar.
        - In other words, if we have already found a path to reach the destination cell with maxSoFar\text{maxSoFar}maxSoFar, then, we would only explore other paths if it takes efforts less than maxSoFar\text{maxSoFar}maxSoFar.
     */

    /*
    Let m be the number of rows and n be the number of columns
    in the matrix heights.

    Time Complexity : O(3m⋅n). The total number of cells in the matrix is given by m⋅nm \cdot nm⋅n.

        "Still" Failed case using backtracking
        The time complexity is exponential,
        hence this approach is exhaustive
        and results in Time Limit Exceeded (TLE).

        https://leetcode.com/submissions/detail/576361924/testcase/
     */

    //global minimum of required max effort in the paths which has been gone through...
    int maxSofar = Integer.MAX_VALUE;


    private int backtrack(int i,int j, boolean[][] path, int[][] heights, int rn, int cn , int currMaxEffort){
        if(i==0 && j==0){//src
            /*
                @Leetcode tricks: update maxSofar once reaching the src
            */
            maxSofar = Math.min(maxSofar, currMaxEffort);

            return currMaxEffort;
        }

        List<int[]> nextMoves = new LinkedList<>();
        if(i-1>=0){
            nextMoves.add(new int[]{i-1, j});
        }

        if(i+1<rn){
            nextMoves.add(new int[]{i+1, j});
        }

        if(j-1>=0){
            nextMoves.add(new int[]{i, j-1});
        }

        if(j+1<cn){
            nextMoves.add(new int[]{i, j+1});
        }

        int min = Integer.MAX_VALUE;
        for(int[] move: nextMoves){
            final int i2=move[0];
            final int j2=move[1];
            if(!path[i2][j2]){ //isValid
                //add to current path
                path[i2][j2] = true;

                //required effort for next move
                int cost = Math.abs(heights[i][j] - heights[i2][j2]);
                //new required max effort in the path
                cost = Math.max(cost, currMaxEffort);


                /*
                @Leetcode tricks: advanced pruning
                        - compare with global variable maxSofar
                 */
                if(cost<maxSofar){ //must be greater than global "min of max effort so far"
                    //carry forward the cost in the next call
                    //choose the path with less effort
                    min=Math.min(min, backtrack(i2,j2, path, heights, rn, cn, cost));
                }


                //backtrack
                path[i2][j2] = false;
            }
        }

        return min;
    }
}
