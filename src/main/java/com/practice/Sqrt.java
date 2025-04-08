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
        /*
            Constraints:
                0 <= x <= 2^31 - 1

            edge case: x = 2147395599
                When calculating mid * mid, if mid becomes too large,
                this multiplication overflows the range of int (which is -2,147,483,648 to 2,147,483,647).
                This causes the result to become a negative value or produce unintended results,
                breaking the logic of your condition checks like mid * mid <= x.
            Solution:
                In the expression (long) mid * mid, the casting to long happens first.
                (long) mid: The variable mid (which is of type int) is first cast to the long type. This ensures that any arithmetic operation involving mid will be carried out in the wider long range.
                Multiplication: After mid is cast to long, the multiplication happens. Since one of the operands ((long) mid) is already a long, the other operand (mid) is automatically promoted to a long as well. The result of the multiplication is a long.
         */
        int i=0, j=x;
        while(i<=j){
            int mid = i + (j-i)/2;
            if((long)mid*mid<=x && x<(long)(mid+1)*(mid+1)){
                return mid;
            } else if((long)mid*mid<x){
                i=mid+1;
            } else {
                j=mid-1;
            }
        }

        throw new ArithmeticException();
    }
}
