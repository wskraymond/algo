package com.mine.greedy;

import java.util.Arrays;

public class CoinChangeInSpecialSetup {
    private int[] deno = { 1, 2, 5, 10, 20,
            50, 100, 500, 1000 };

    /**
     * https://www.geeksforgeeks.org/greedy-algorithm-to-find-minimum-number-of-coins/
     *
     * https://codeforces.com/problemset/problem/996/A
     *
     * https://www.youtube.com/watch?v=bf_bRSPR3D0
     *
     * Given a value V, if we want to make a change for V Rs,
     * and we have an infinite supply of each of the denominations in Indian currency,
     * i.e., we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes,
     * what is the minimum number of coins and/or notes needed
     * to make the change?
     *
     * Input: V = 70
     * Output: 2
     * We need a 50 Rs note and a 20 Rs note.
     *
     * Input: V = 121
     * Output: 3
     * We need a 100 Rs note, a 20 Rs note and a 1 Rs coin.
     *
     * @param amount
     * @return
     */
    public int coinChange(int amount) {
        Arrays.sort(deno);

        int count = 0;
        for(int tmp=amount, i=deno.length-1;tmp>0;tmp-=deno[i]){
            while(deno[i]>tmp){
                i--;
            }

            count++;
        }

        return count;
    }
}
