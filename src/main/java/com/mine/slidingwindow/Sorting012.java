package com.mine.slidingwindow;

public class Sorting012 {
    /**
     * input : 0 1 0 0 1 2 2 1 0
     * output : 0 0 0 0 1 1 1 2 2
     *
     * target: O(n)
     */

    public int[] sort012(int[] arr) {
        int zeros = 0, ones = 0, twos = 0;
        for(int i=0; i<arr.length; i++ ) {
            if(arr[i]==0)
                zeros++;
            else if(arr[i]==1)
                ones++;
            else if(arr[i]==2)
                twos++;
        }

        for(int i=0; i<arr.length; i++ ) {
            if(zeros>0) {
                arr[i] = 0;
                zeros--;
            } else if(ones>0) {
                arr[i] = 1;
                ones--;
            } else if(twos>0) {
                arr[i] = 2;
                twos--;
            }
        }

        return arr;
    }
}
