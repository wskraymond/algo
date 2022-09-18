package com.mine.dp.palindrome.counting;

public class CountingPalindromicSubstr_outward {
    /**
     * Given a string s, return the number of palindromic substrings in it.
     *
     * A string is a palindrome when it reads the same backward as forward.
     *
     * A substring is a contiguous sequence of characters within the string.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abc"
     * Output: 3
     * Explanation: Three palindromic strings: "a", "b", "c".
     *
     * Example 2:
     *
     * Input: s = "aaa"
     * Output: 6
     * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if(s==null || s.length()==0){
            return 0;
        }

        int count=0;
        int n = s.length();
        for(int i=0;i<n;i++){
            //odd
            int l=i,r=i;
            while(l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
                count++;
                l--;
                r++;
            }

            //even
            l=i; r=i+1;
            while(l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
                count++;
                l--;
                r++;
            }
        }

        return count;
    }
}
