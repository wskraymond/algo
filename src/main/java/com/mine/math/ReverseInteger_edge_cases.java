package com.mine.math;

public class ReverseInteger_edge_cases {
    /**
     Input
     1534236469
     Output
     1056389759
     Expected
     0

     Constraints:

     -2^31 <= x <= 2^31 - 1
     */

    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3
    //% is remainder operator but not modulus
    // 1%2 = 1
    // -1%2 = -1
    // 1%(-2) = 1
    public int reverse(int x) {
        final int min = Integer.MIN_VALUE/10, max = Integer.MAX_VALUE/10;
        final int minFirstDigit = Integer.MIN_VALUE%10, maxFirstDigit = Integer.MAX_VALUE%10;
        int reverse = 0;
        int current = x;
        do {
            int firstDigit = current%10;
            current/=10;

            /**
             * Checking if below operation leads to overflow
             */
            if(reverse < min || reverse==min && firstDigit < minFirstDigit ){
                return 0;
            }

            if(reverse > max || reverse==max && firstDigit > maxFirstDigit ){
                return 0;
            }

            reverse*=10;
            reverse+=firstDigit;
        }while (current!=0);

        return reverse;
    }
}
