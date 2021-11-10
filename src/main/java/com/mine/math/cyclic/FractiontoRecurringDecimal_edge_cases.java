package com.mine.math.cyclic;

import java.util.HashMap;
import java.util.Map;

public class FractiontoRecurringDecimal_edge_cases {
    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3
    //% is remainder operator but not modulus
    // 1%2 = 1
    // -1%2 = -1
    // 1%(-2) = 1

    /**
     * Failure of edge cases from first version
     *
     * Input
     * -50
     * 8
     * Output
     * "-6"
     * Expected
     * "-6.25"
     *
     *
     * Input
     * 7
     * -12
     * Output
     * "0.58(3)"
     * Expected
     * "-0.58(3)"
     *
     * Input
     * 0
     * -5
     * Output
     * "-0"
     * Expected
     * "0"
     *
     * Input
     * -1
     * -2147483648
     * Output
     * "0.000000000000"
     * Expected
     * "0.0000000004656612873077392578125"
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder integer = new StringBuilder("");
        StringBuilder fraction= new StringBuilder("");
        Map<Long,Integer> cyclicMap= new HashMap();

        /**
         * XOR truth table (^ Operator)
         * Input	Output
         * A	B
         * 0	0	0
         * 0	1	1
         * 1	0	1
         * 1	1	0
         */
        String sign = numerator<0 ^ (numerator!=0 && denominator <0) ? "-" : "";
        integer.append(sign);

        /**
         * # Integer.MIN_VALUE in Math.abs will overflow
         *
         * -2147483648 =-2^31 <= numerator, denominator <= 2^31 - 1
         *
         * Convert to Long or else abs(-2147483648) overflows
         */
        /**
         * Thus , the only reason for use of long type is avoid overflow of Math.abs
         * But Not other calculation's overflow (division makes remainder or quotient smaller )
         */
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        long quotient = dividend/divisor;
        long remainder = dividend % divisor; // or numerator - quotient * denominator // 1%7 = 1

        integer.append(quotient);

        int i = 0;
        cyclicMap.put(remainder, i);
        int cyclicStart = -1;
        while(remainder>0){
            long newDiv = remainder * 10;
            quotient = newDiv / divisor;
            remainder = newDiv % divisor; // or numerator - quotient * denominator

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

        return fraction.length()!=0 ?  integer.append(".").append(fraction).toString() : integer.toString();
    }
}
