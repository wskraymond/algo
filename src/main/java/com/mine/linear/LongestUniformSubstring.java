package com.mine.linear;

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
       int max = 0, size=0;
       String result = null;
       for(int i=0, j=0;j<str.length();j++) {   //i, j index for str
           if(str.charAt(i)!=str.charAt(j)){
               if(j-i > max){
                   max = j-i;
                   result = str.substring(i,j); //length is j (next different char)
               }
               i=j;  //reset index i
           }

           if(j == str.length() -1 ){
               if(j+1-i > max){
                   max = j+1-i;
                   result = str.substring(i,j+1); // length is end index + 1 (str size)
               }
           }
       }

       return result;
    }
}
