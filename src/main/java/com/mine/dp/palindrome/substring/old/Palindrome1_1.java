package com.mine.dp.palindrome.substring.old;

/**
 * dp(i,i)=true for [a], and default return :""
 * dp(i,i+1)=Si==Si+1
 * dp(i,j) = dp(i+1,j-1) && Si==Sj
 *
 * by
 */
public class Palindrome1_1 {
    public String longestPalindrome(String s) {
        int max = 0;
        String result = "";
        for(int i=0;i<s.length();i++) {
            for(int j=i;j<s.length();j++) {
                if(dp(s, i, j))
                {
                    int size = j-i+1;
                    if(size>max)
                    {
                        max = size;
                        result = s.substring(i,j+1);
                    }

                }
            }
        }

        return result;
    }

    boolean dp(String s, int i, int j){
        if(i==j){
            return true;
        } else {
            boolean same = s.charAt(i) == s.charAt(j);
            if(i+1==j){
                return same;
            } else {
                return dp(s, i+1,j-1) && same;
            }
        }
    }
}
