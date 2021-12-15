package com.mine.dp;

public class Knapsack01 {
    /**
     * @param capacity - The maximum capacity of the knapsack
     * @param w - The weights of the items
     * @param v - The values of the items
     * @return The maximum achievable profit of selecting a subset of the elements such that the
     *     capacity of the knapsack is not exceeded
     */
    public int optimalVal(int capacity, int[] w, int[] v){
        return dp(capacity, w, v)[w.length][capacity];
    }

    public boolean[] optimalSol(int capacity, int[] w, int[] v){
        int[][] dp = dp(capacity, w, v);

        boolean[] isSelected = new boolean[w.length];
        for(int i=w.length, j=capacity; i>=1; i--){
            if(dp[i][j]!=dp[i-1][j]){
                isSelected[i-1]=true;
                j -= w[i-1];    //It must satisfy the condition: j >= w[i-1]
            }
        }

        return isSelected;
    }

    public int[][] dp(int capacity, int[] w, int[] v){
        /**
         * Recurrence Relation:
         *      f(i,c) = max{f(i-1,c), f(i-1, c-wi) + vi}
         *
         * Base Case:
         *      f(i,0) = 0
         *      f(0,c) = 0
         */
        if(capacity < 0 || w==null || v==null || w.length!=v.length){
            throw new IllegalArgumentException("invalid Input");
        }

        int n = w.length; //n items
        int[][] dp = new int[n+1][capacity+1];

        for(int i=1; i<=n; i++){
            int wi=w[i-1], vi=v[i-1];
            for(int j=1; j<=capacity;j++){
                dp[i][j]=Math.max(dp[i-1][j], wi<=j ?
                        dp[i-1][j-wi] + vi : dp[i-1][j]);
            }
        }

        return dp;
    }


}
