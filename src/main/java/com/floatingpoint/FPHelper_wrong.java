package com.floatingpoint;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FPHelper_wrong {
    private static final DecimalFormat DEFAULT_FORMAT = new DecimalFormat("0.00");
    private static final DecimalFormat DEFAULT_FORMAT_TRAILING_ZEROES = new DecimalFormat("0.##");

    public FPHelper_wrong() {
        /**
         * DecimalFormat provides rounding modes defined in RoundingMode for formatting.
         * by default, it uses RoundingMode.HALF_EVEN.
         */
        DEFAULT_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
        DEFAULT_FORMAT_TRAILING_ZEROES.setRoundingMode(RoundingMode.HALF_UP);
    }

    public double round(double value){
        return Math.round(value*100)/100.0;
    }

    public String roundToStr(double v){
        return DEFAULT_FORMAT.format(v);
    }

    public String roundToStrWithoutTrailingZeros(double v){
        return DEFAULT_FORMAT_TRAILING_ZEROES.format(v);
    }
}
