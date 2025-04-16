package com.mine.twopointers.twostring;

public class LongPressName_no_nested {
    public boolean isLongPressedName(String name, String typed) {
        final int n = name.length();
        final int m = typed.length();
        if(n==m){
            return name.equals(typed);
        }

        if(n>m){
            return false;
        }

        int i=0,j=0;
        while(j<m){
            if(i<n && name.charAt(i)==typed.charAt(j)){
                i++;
            } else if(j==0 || typed.charAt(j-1)!=typed.charAt(j)){ //mismatch
                return false;
            }

            j++;
        }//end when j==m

        return i==n;
    }
}
