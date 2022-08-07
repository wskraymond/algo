package com.mine.backtracking.combination;

public class NcR {
    public int nCr(int n, int r){
        return dfs(0, r, n);
    }

    private int dfs(int i, int r, int n){
        if(r==0){
            return 1;
        }

        if(i==n){
            return 0;
        }

        return dfs(i+1, r-1, n) + dfs(i+1, r, n);
    }
}
