package com.mine.math.cyclic;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber100 {
    /**
     * A happy number is a number defined by the following process:
     *
     *     Starting with any positive integer,
     *        - replace the number by the sum of the squares of its digits.
     *     Repeat the process until
     *          1. the number equals 1 (where it will stay),
     *          2. or it loops endlessly in a cycle which does not include 1.
     *     Those numbers for which this process ends in 1
     *        - are happy.
     *
     * Return true if n is a happy number, and false if not.
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        /**
         * Based on our exploration so far, we'd expect continually following links to end in one of three ways.
         *
         * It eventually gets to 11.
         * It eventually gets stuck in a cycle.
         * It keeps going higher and higher, up towards infinity.
         * That 3rd option sounds really annoying to detect and handle. How would we even know that it is going to continue going up, rather than eventually going back down, possibly to 1?1? Luckily, it turns out we don't need to worry about it. Think carefully about what the largest next number we could get for each number of digits is.
         *
         * Digits	Largest	Next
         * 1	9	81
         * 2	99	162
         * 3	999	243
         * 4	9999	324
         * 13	9999999999999	1053
         */

        int current = n;

        Set<Integer> s = new HashSet<>();
        do {
            s.add(current);
            int tmp = 0;
            do {
                int digit = current%10;
                current/=10;
                tmp+=digit*digit;  //power in java is NOT ^, but Math.pow()
            } while (current != 0);
            current = tmp;
        }while(current!=1 && !s.contains(current));

        /**
         * Time complexity : O(logn).
         */
        return current==1;
    }
}
