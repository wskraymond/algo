package com.mine.backtracking.combination.bound;

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
        /*
         Follow up: candidate-iterative backtracking vs DFS(take&skip recursive) Backtracking

         */
        return  //DFS(take&skip recursive) Backtracking
                dfs(i+1, r-1, n)    // take the candidate
                        + dfs(i+1, r, n);   //skip the candidate
    }
}
