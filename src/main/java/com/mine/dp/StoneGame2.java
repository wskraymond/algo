package com.mine.dp;

/**
 * divide and conquer with recursion
 */
public class StoneGame2 {
    public boolean play(int[] piles){
        return aMinusB(piles, 0, piles.length-1) > 0;
    }

    /** 2 plays: Alex and Lee
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
     * @param i
     * @param j
     * @return A's max score minus B's max score
     */
    public int aMinusB(int[] piles , int i , int j){
        //length : 1 2 3 4
        //index  : 0 1 2 3
        //pointer: i     j
        //(3 + 0 + 4) % 2 = (7) % 2 = 1 (Alex's round)
        int parity = (i + j + piles.length) % 2;

        //A1,A2,B2,B1
        //1,2,3,4
        //iA1,iA2,jB2,jB1
        if(i==j) { //the last pile
            return parity==1 ? piles[i] : -piles[i];
        }

        if(parity==1) // User A's round
            return Math.max(piles[i] + aMinusB(piles,i+1,j), piles[j] + aMinusB(piles,i,j-1));
        else //User B // User B's round
            return Math.max(-piles[i] + aMinusB(piles,i+1,j), -piles[j] + aMinusB(piles,i,j-1));
    }
}
