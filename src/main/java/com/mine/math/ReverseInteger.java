package com.mine.math;

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
        int reverse = 0;
        int current = num;
        do {
            int tmp = (current/10);
            int remainder = current - tmp*10;
            reverse *=10;
            reverse +=remainder;
            current = tmp;
        }while (current!=0);

        return reverse;
    }
}
