package com.mine.math;

import java.util.HashMap;
import java.util.Map;

public class DivisionToString {
    /**
     * 1) dividend=5 , divisor = 2  => 2.5 => Str: 2.5
     * 2) dividend=1 , divisor = 3  => 0.3333333 => Str: 0.(3)   //cyclic decimal -> 3
     * 3) dividend=1 , divisor = 6  => 0.1666666 => Str: 0.1(6)  //cyclic decimal -> 6
     * 4) dividend=1 , divisor = 100  => 0.01 => Str: 0.01
     * 5) dividend=1 , divisor = 7  => 0.142857142857 => Str: 0.(142857)
     * 6) dividend=1 , divisor = 70  => 0.0142857142857 => Str: 0.0(142857)
     * 8) dividend=71 , divisor = 7  => 10.0142857142857 => Str: 0.0(142857)
     */

    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3
    //% is remainder operator but not modulus
    // 1%2 = 1
    // -1%2 = -1
    // 1%(-2) = 1

    public String play(int dividend, int divisor) {
        String integer = "";
        StringBuilder fraction= new StringBuilder("");
        Map<Integer,Integer> cyclicMap= new HashMap<Integer, Integer>();

        int quotient = dividend/divisor;
        int remainder = dividend % divisor; // or dividend - quotient * divisor // 1%7 = 1

        integer = String.valueOf(quotient);

        int i = 0;
        cyclicMap.put(remainder, i);
        int cyclicStart = -1;
        while(remainder>0){
            int newDiv = remainder * 10;
            quotient = newDiv / divisor;
            remainder = newDiv % divisor; // or dividend - quotient * divisor

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

        return integer + "." + fraction;
    }
}
