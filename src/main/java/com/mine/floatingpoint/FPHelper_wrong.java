package com.mine.floatingpoint;

import java.text.DecimalFormat;

public class FPHelper_wrong {
    public DecimalFormat format = new DecimalFormat("0.00");

    public double round(double value){
        return Math.round(value*100)/100.0;
    }

    public String roundToStr(double value){
        return format.format(value);
    }
}
