package com.mine.twopointers.twostring;

public class LongPressName_mine {
    public boolean isLongPressedName(String name, String typed) {
        final int n = name.length();
        final int m = typed.length();
        int i=0,j=0;
        while(i<n){
            if(j>=m){
                return false;
            }

            if(name.charAt(i)!=typed.charAt(j)){
                while(j-1>=0 && j<m && typed.charAt(j-1)==typed.charAt(j)){
                    j++;
                }

                if(j>=m || name.charAt(i)!=typed.charAt(j)){
                    return false;
                }
            }

            i++;
            j++;
        }

        while(j<m && typed.charAt(j-1)==typed.charAt(j)){
            j++;
        }


        return i==n && j==m;
    }
}
