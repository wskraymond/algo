package com.ms;

import java.util.Arrays;
import java.util.Collections;

public class MinRound {

    public int play(int[] arr, int bagSize){
        boolean[] used = new boolean[arr.length];
        Arrays.fill(used, false);
        for(int i=0;i<arr.length;i++){
            if(!used[i]) {
                int remaining = bagSize - arr[i];
                int pair = min(used, arr, bagSize, remaining);
            }
        }

        return 0;
    }

    private int min(boolean[] used,int[] arr, int bagSize,int remaining ){
        int min = -1;
        int matched = -1;
        for (int j = 0; j < arr.length; j++) {
            if(!used[j]) {
                int diff = remaining - arr[j];
                if(diff>=0 && diff<=min){
                    min=diff;
                    matched = j;
                }
            }
        }
        return matched;
    }
}
