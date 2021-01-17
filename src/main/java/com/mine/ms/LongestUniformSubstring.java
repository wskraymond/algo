package com.mine.ms;

public class LongestUniformSubstring {
    /**
     * case 0
     * 0123456
     * aaaaaac
     *
     * case 1
     * 01234567
     * abbbccda
     * bbb => [1,3]
     *
     * case 2
     * 01234567
     * accccccc
     */

    public String play(String str){
        int i=0, j=1;
        int start=i , end=j;
        char last = str.charAt(0);
        int max = 1;
        for(int k=1;k<str.length();k++){
            if(str.charAt(k)!=last){
                last = str.charAt(k);
                i=k;
                j=i+1;
            } else {
                j++;
            }

            if(j-i>max) {
                start = i;
                end = j;
                max = end - start;
            }
        }

        return str.substring(start, end);
    }
}
