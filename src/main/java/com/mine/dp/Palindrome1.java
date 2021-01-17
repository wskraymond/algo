package com.mine.dp;

/**
 * brute force , 3 loop
 */
public class Palindrome1 {
    public String longestPalindrome(String s) {
        int max = 0;
        String result = "";

        //the number of combination = C(N,2)
        //dp(i,j)
        //O(N^3)
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String sub = s.substring(i,j+1);
                int size = sub.length();
                int center = size%2==0 ? size/2 : (size+1)/2;
                boolean valid = true;
                for(int k =0 ; k<center;k++) {
                    if(sub.charAt(k)!=sub.charAt(size-1-k))
                    {
                        valid = false;
                        break;
                    }
                }

                if(valid && size > max){
                    max = size;
                    result = sub;
                }
            }
        }

        return result;
    }
}
