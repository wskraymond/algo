package com.practice;

public class Sqrt {
    /**
     * case 1
     * input:
     * 1 2 3 4 5 6 ......................36
     * 1 4 9 16 25 36 .....................
     *
     * 0 <= sqrt(x) <= x
     * @param x
     * @return Given a non-negative integer x,
     *      return the square root of x rounded down to the nearest integer.
     *      The returned integer should be non-negative as well.
     */
    public int play(int x){
        int i=0, j=x;
        while(i<=j){
            int mid = i + (j-i)/2;
            if(mid*mid<=x && x<(mid+1)*(mid+1)){
                return mid;
            } else if(mid*mid<x){
                i=mid+1;
            } else {
                j=mid-1;
            }
        }

        throw new ArithmeticException();
    }
}
