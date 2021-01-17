package com.mine.math;

public class MyPow {
    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3
    //% is remainder operator but not modulus
    // 1%2 = 1
    // -1%2 = -1
    // 1%(-2) = 1

    /*
    below is my version , it works but it is not elegant in code
    refer to https://leetcode.com/problems/powx-n/discuss/19544/5-different-choices-when-talk-with-interviewers
     */

    public double play(double x, int n) {
        if(n==0)
            return 1;

        double result = 1;
        double base = x;
        int pow = n;  // can't use absolute , becoz Math.abs(-2147483648) = 1
        while(pow!=0){
            if(pow != (pow/2)*2){ //can't use pow%2==1 becos -3%2==-1 but can use pow%2!=0 !!!!!!!!
                result*=base;
            }
            pow/=2;
            base*= base;
        }

        System.out.println(result);
        return n>0 ? result : 1/result;
    }

    /*
    below is online version
     */
    double myPow(double x, int n) {
        if(n==0) return 1;
        if(n<0){
            n = -n;
            x = 1/x;
        }
        return n%2==0 ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }

}
