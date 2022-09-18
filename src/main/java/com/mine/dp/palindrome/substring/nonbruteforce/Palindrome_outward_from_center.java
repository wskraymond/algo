package com.mine.dp.palindrome.substring.nonbruteforce;

/**
 * expanding around center (the number of center for string with size=N = 2N-1)
 * center type 1: dp(i,i)=true for [a], and default return :""
 *      - expanding: as dp(i,i)=true, then expand to dp(i-1,i+1) if and only if i-1 >=0 && i+1<N
 * center type 2: dp(i,i+1)=Si==Si
 *      - expanding: if dp(i,i+1)==true, then expand to dp(i-1,i+2)
 *  dp(i,j) = dp(i+1,j-1) && Si==Sj

 */
public class Palindrome_outward_from_center {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0){
            return "";
        }

        int n = s.length();
        int start=0, end=0; //initial substring = empty
        for(int i=0;i<n;i++){
            //odd
            int l=i, r=i;
            while(l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
                //alternative, beware that r and l is inclusive index here
                /*if(r-l+1>end-start){
                    start=l;
                    end=r+1;
                }*/
                l--;
                r++;
            }

            //exclusive l and r index
            if(r-l-1>end-start){
                start=l+1; //inclusive index - start
                end=r;     //exclusive index - end
            }

            //even
            l=i; r=i+1;
            while(l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
                //alternative
                /*if(r-l+1>end-start){
                    start=l;
                    end=r+1;
                }*/
                l--;
                r++;
            }
            //exclusive l and r index
            if(r-l-1>end-start){
                start=l+1; //inclusive index - start
                end=r;     //exclusive index - end
            }
        }

        return s.substring(start, end);
    }
}
