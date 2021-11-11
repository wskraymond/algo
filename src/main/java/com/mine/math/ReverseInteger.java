package com.mine.math;

public class ReverseInteger {
    /**
     Given a signed 32-bit integer x,
        - return x with its digits reversed.

     If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1],
        - then return 0.

     ##Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

     Example 1:

     Input: x = 123
     Output: 321
     Example 2:

     Input: x = -123
     Output: -321
     Example 3:

     Input: x = 120
     Output: 21
     Example 4:

     Input: x = 0
     Output: 0


     Constraints:

     -2^31 <= x <= 2^31 - 1
     */

    public int reverse(int x) {
        int reverse = 0;
        int current = x;
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
