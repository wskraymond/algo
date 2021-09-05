package com.mine.greedy;

import java.util.Arrays;

public class FractionalKnapsack {
    public double optimalVal(int capacity, int[] w, int[] v){
        int n = w.length;
        final int wi=0, vi=1;

        int[][] items = new int[n][2];
        for(int i=0;i<n;i++){ //O(n)
            items[i][wi]=w[i];
            items[i][vi]=v[i];
        }

        //o(nlogn)
        Arrays.sort(items, (i1, i2)-> Double.compare((double)i1[wi]/(double)i1[vi],
                (double)i2[wi]/(double)i2[vi]));

        double result =0;
        for(int i=0, j=capacity; i<n && j>0;i++){ //O(n)
            if(items[i][wi] <= j){
                result+=items[i][vi];
                j-=items[i][wi];
            } else{
                double ratio = (double)items[i][vi]/items[i][wi];
                result+= j*ratio;
                j=0;
            }
        }

        return result;  //O(n) + O(nlogn) + O(n) = O(nlogn)
    }
}
