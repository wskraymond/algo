package com.mine.dp;

/**
 * use 2d array to store every result of dp(i,j) from bottom up
 * and find dp(i,n)
 */
public class StoneGame3 {
    public boolean play(int[] piles){
        return dp(piles) > 0;
    }

    /**
     * Non-Recursion Method to solve
     *
     * 2 plays: Alex and Lee
     * 0. A plays first round
     * 1. dp(i,j): To find largest score Alex can achieve where the piles remaining are piles[i], piles[i+1], ..., piles[j]
     * 2. A and B play it optimally
     * 3. Alex's possible maximum possible score != Alex wins ,
     *    but the longest path Alex can take is greater than Lee's longest possible path = ALex wins
     *
     * => Competition: in order to fight for possible maximum possible score, the following condition should be met.
     *      - Round X: Alex always takes the max{piles[i] + dp(i+1,j), piles[j] + dp(i,j-1)}
     *      - Round X + 1: Lee always takes the max{piles[i] + dp(i+1,j), piles[j] + dp(i,j-1)}
     *
     * => Determination: in order to determine if Alex's score is greater than Lee's
     *     - Alex's score - Lee's score > 0 <===> sum{ +Alex's pile at each round - Lee's pile at each round } > 0
     *
     * @param piles
     * @return A's max score minus B's max score
     */
    public int dp(int[] piles){
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        // N = 4
        //length  : 1 2 3 4 5 6 => N + 2
        //index   : 0 1 2 3 4 5
        //value   : 0 3 4 9 1 0
        //pointer :   i     j
        //i=1 => piles[1-1]=0 and j=4 => piles[4+1]=0
        //so dp[0][0]=0 and dp[5][5]=0 as stop point in recursion method , but here is array element
        int[][] dp = new int[N+2][N+2];

        //bottom up strategy for dynamic programming
        //size 1:
        //      i: 0 , j:0 => dp[1][1] = max{piles[0] + dp[2][1], piles[0] + dp[1][0]}
        //      i: 1 , j:1 => dp[2][2] = min{-piles[1] + dp[3][2], -piles[1] + dp[2][1]}
        //      ....
        //      i: N-1, j:N-1 => dp[N][N] = min{-piles[N-1] + dp[N+1][N], -piles[N-1] + dp[N][N-1]}
        // result: dp[1][1]= piles[0], dp[2][2]=-piles[1]
        //size 2:
        //      i: 0 , j:1 => dp[1][2] = max{piles[0] + dp[2][2], piles[1] + dp[1][1]}
        //      i: 1 , j:2 => dp[2][3] = max{piles[1] + dp[3][3], piles[2] + dp[2][2]}
        //      ...
        //      i:N-2, j:N-1 => dp[N-1][N] = max{piles[N-2] + dp[N][N], piles[N-1] + dp[N-1][N-1]}
        // result: ...........

        //Generally, it traverse with size from i to N-size
        //........i<size...j(=i+size-1)>
        //for N=4
        //index 0 1 2 3
        //size=1, (0,0)->(1,1)->(2,2)->(3,3)
        //size=2, (0,1)->(1,2)->(2,3)
        //size=3, (0,2)->(1,3)
        //size=4, (0,3)
        for (int size = 1; size <= N; ++size)  // 1 to N piles , start from the last remaining pile in the last round
            for (int i = 0; i + size <= N; ++i) { //beware: it is less and @equal@ => 3 + 1 = 4
                //index  : 0...i,i+1,....,S-1,S
                //pointer:     i          j
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }
        /**
         * piles: [4,5]
         * rows: i , columns: j
         * what we observed :
         *      1. diagonal line: dp[1,1]=-4 , dp[2,2]=-5 , it means the last piles was picked by User B
         *      2. dp[1][2] = 1 = 5(User A) - 4(User B)    //User A won't choose 4 at the round 1 but 5
         *      3. dp[2][1] = 0 => out of bound combination: j....i //i>j -> i crosses j
         * 0 0 0 0
         * 0 -4 1 0
         * 0 0 -5 0
         * 0 0 0 0
         */
        for(int i=0;i<N+2;++i) {
            for (int j = 0; j < N + 2; ++j)
                System.out.print(dp[i][j] + " ");
            System.out.println();
        }
        return dp[1][N]; // i:0 <--->j:N-1
    }
}
