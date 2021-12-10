package com.mine.dp.palindrome.longestsubstr;

/**
 * rule 1: dp(i,i)=true for [a], and default return :""
 * rule 2:dp(i,i+1)=Si==Si+1
 * rule 3: dp(i,j) = dp(i+1,j-1) && Si==Sj
 *
 * Topic: 2d array versus hashmap
 * 1. for hashmap, we know whether it contains the result dp(i,j) or not
 * 2. for 2d array, if and only we can define a value which is not contained in the the set of
 *    all possible return value from recursive function
 *      - we can't use getbyKey(i,j) like map
 *      - AND from rule 3: dp(i,j) = dp(i+1,j-1) && Si==Sj
 *          - used in recursion like fibonaci see algo.txt
 *          - OR traverse from bottom up
 *              - generally, traverse from size 1 to size N (imagine a subArray with size traverse from i to N-size)
 *              //for N=4
 *              //index 0 1 2 3
 *              //size=1, (0,0)->(1,1)->(2,2)->(3,3)  => all true
 *              //size=2, (0,1)->(1,2)->(2,3)
 *              //size=3, (0,2)->(1,3) <- it needs size 1 result
 *              //size=4, (0,3)   <- it needs size 2 result    (size-2) result
 *
 *              //implies: level at certain size , it needs (size-2) result
 */
public class Palindrome2 {
    public String longestPalindrome(String s) {
        int max = 0;
        int start = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int size=1;size<=s.length();size++)
        {
            for(int i=0, j = i+size-1 ;j<s.length();j=++i+size-1)
            {
//                int j = i+size-1;
                if(i==j)
                {
                    dp[i][j]=true;
                } else {
                    boolean same = s.charAt(i) == s.charAt(j);
                    if(i+1==j){
                        dp[i][j]= same;
                    } else {
                        dp[i][j]=dp[i+1][j-1] && same;
                    }
                }

                if(dp[i][j] && size>max){
                    max = size; //**********don't get to set
                    start = i;
                }
            }
        }

        return s.substring(start, start+max);
    }

}
