package com.mine.dp.palindrome.substring.nonbruteforce.old;

/**
 * expanding around center (the number of center for string with size=N = 2N-1)
 * center type 1: dp(i,i)=true for [a], and default return :""
 *      - expanding: as dp(i,i)=true, then expand to dp(i-1,i+1) if and only if i-1 >=0 && i+1<N
 * center type 2: dp(i,i+1)=Si==Si
 *      - expanding: if dp(i,i+1)==true, then expand to dp(i-1,i+2)
 *  dp(i,j) = dp(i+1,j-1) && Si==Sj

 */
public class Palindrome_outward_from_center_old {
    public String longestPalindrome(String s) {
        int n = s.length();
        String result = "";
        int max = 0;
        for(int i=0;i<s.length();i++){
            for(int x=i,y=i; x>=0&&y<n;x--, y++)
            {
                int size = y-x+1;

                if(!check(s,x,y))
                    break;

                if(size>max)
                {
                     max = size;
                     result = s.substring(x,y+1);
                }
            }

            if(i+1<s.length()) {
                for (int x = i, y = i + 1; x >= 0 && y < n; x--, y++) {
                    int size = y - x + 1;

                    if(!check(s,x,y))
                        break;

                    if (size > max) {
                        max = size;
                        result = s.substring(x, y + 1);
                    }
                }
            }
        }
        return result;
    }

    boolean check(String s, int x,int y){
        return s.charAt(x)==s.charAt(y);
    }

}
