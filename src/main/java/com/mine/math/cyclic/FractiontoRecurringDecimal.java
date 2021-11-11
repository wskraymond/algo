package com.mine.math.cyclic;

import java.util.HashMap;
import java.util.Map;

public class FractiontoRecurringDecimal {
    /**
     * 1) numerator=5 , denominator = 2  => 2.5 => Str: 2.5
     * 2) numerator=1 , denominator = 3  => 0.3333333 => Str: 0.(3)   //cyclic decimal -> 3
     * 3) numerator=1 , denominator = 6  => 0.1666666 => Str: 0.1(6)  //cyclic decimal -> 6
     * 4) numerator=1 , denominator = 100  => 0.01 => Str: 0.01
     * 5) numerator=1 , denominator = 7  => 0.142857142857 => Str: 0.(142857)
     * 6) numerator=1 , denominator = 70  => 0.0142857142857 => Str: 0.0(142857)
     * 8) numerator=71 , denominator = 7  => 10.0142857142857 => Str: 0.0(142857)
     */

    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3
    //% is remainder operator but not modulus
    // 1%2 = 1
    // -1%2 = -1
    // 1%(-2) = 1

    /**
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     *
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     *
     * If multiple answers are possible, return any of them.
     *
     * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
     *
     *
     *
     * Example 1:
     *
     * Input: numerator = 1, denominator = 2
     * Output: "0.5"
     * Example 2:
     *
     * Input: numerator = 2, denominator = 1
     * Output: "2"
     * Example 3:
     *
     * Input: numerator = 2, denominator = 3
     * Output: "0.(6)"
     * Example 4:
     *
     * Input: numerator = 4, denominator = 333
     * Output: "0.(012)"
     * Example 5:
     *
     * Input: numerator = 1, denominator = 5
     * Output: "0.2"
     *
     *
     * Constraints:
     *
     * -231 <= numerator, denominator <= 231 - 1
     * denominator != 0
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        String integer = "";
        StringBuilder fraction= new StringBuilder("");
        Map<Integer,Integer> cyclicMap= new HashMap<Integer, Integer>();

        int quotient = numerator/denominator;
        int remainder = numerator % denominator; // or numerator - quotient * denominator // 1%7 = 1

        integer = String.valueOf(quotient);

        int i = 0;
        cyclicMap.put(remainder, i);
        int cyclicStart = -1;
        while(remainder>0){
            int newDiv = remainder * 10;
            quotient = newDiv / denominator;
            remainder = newDiv % denominator; // or numerator - quotient * denominator

            fraction.append(quotient);
            if(cyclicMap.containsKey(remainder)) {
                cyclicStart = cyclicMap.get(remainder);
                break;
            } else {
                cyclicMap.put(remainder, ++i); //store
            }
        }

        if(cyclicStart!=-1) {
            fraction.insert(cyclicStart, "(");
            fraction.append(")");
        }

        return fraction.length()!=0 ?  integer + "." + fraction : integer;
    }
}
