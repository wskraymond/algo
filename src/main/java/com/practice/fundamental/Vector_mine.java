package com.practice.fundamental;

import java.util.Arrays;

public class Vector_mine {
    private final float x;
    private final float y;

    public Vector_mine(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object v){
        if(v==null || this.getClass()!=v.getClass()){
            return false;
        }

        if(this==v){
            return true;
        }

        Vector_mine o = (Vector_mine) v;
        return Float.compare(this.x, o.x)==0 && Float.compare(this.y, o.y)==0;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        for(float val: Arrays.asList(x,y)){
            hash = (hash<<5) - hash + Float.hashCode(val);
        }
        /*
            Goal: inject every member value in an object by randomly/uniform distribution of 0 and 1 on each bit of 32-bit (int)
            0<<5 - 0 + x_int
            x_int<<5 - x_int + y_int
         */

        return hash;
    }
}
