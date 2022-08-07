package com.mine.backtracking.permutation;

public class NpR {
    public int nPr(int n, int r){
        return dfs(r, n);
    }

    private int dfs(int r, int n){
        if(r==0){
            return 1;
        }

        if(n==0){
            return 0;
        }

        return dfs(r-1, n-1)*n;
    }
}
