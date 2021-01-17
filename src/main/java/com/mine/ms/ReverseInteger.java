package com.mine.ms;

public class ReverseInteger {
    /**
     * case 1
     * input: 123
     * output: 321
     *
     * case 2
     * input: -123
     * output: -321
     *
     * case 3
     * input: 120
     * output: 21
     */

    public int play(int num) {
        int result = 0;
        int tmp = num;
        do{
            int d = tmp - tmp/10*10;
            result *=10;
            result +=d;
            tmp/=10;
        }while(tmp/10!=0);

        return result;
    }
}
