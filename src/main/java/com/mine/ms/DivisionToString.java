package com.mine.ms;

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
     * 8) dividend=71 , divisor = 7  => 10.0142857142857 => Str: 10.0(142857)
     */

    public String play(int dividend, int divisor) {
        StringBuilder fraction = new StringBuilder();
        Map<Integer,Integer> m = new HashMap<>();
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        String integer = String.valueOf(quotient);

        int i =0;
        int cyclicStart = -1;
        m.put(remainder, 0);
        while(remainder!=0){
            int newDiv = remainder * 10;
            quotient = newDiv / divisor;
            remainder = newDiv % divisor;
            fraction.append(quotient);

            if(m.containsKey(remainder)){
                cyclicStart = m.get(remainder);
                break;
            } else {
                m.put(remainder, ++i);
            }
        }

        if(cyclicStart >= 0){
            fraction.insert(cyclicStart, "(");
            fraction.append(")");
        }

        return integer + "." + fraction;
    }
}
