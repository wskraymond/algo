package com.mine.jpm;

public class CountBinarySubstrings {
    /**
     * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
     *
     * Substrings that occur multiple times
     * are counted the number of times they occur.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "00110011"
     * Output: 6
     * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
     * Notice that some of these substrings repeat and are counted the number of times they occur.
     * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
     *
     * Example 2:
     *
     * Input: s = "10101"
     * Output: 4
     * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
     *
     *
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        if(s==null || s.length() <2){
            return 0;
        }

        int result = 0;
        int preDnum = 0, currDnum=1;
        char c=s.charAt(0);
        for(int i=1; i<s.length();i++){
            char c2 = s.charAt(i);
            if(c!=c2){
                result+=Math.min(preDnum, currDnum);

                //shift
                preDnum=currDnum;
                currDnum=0;
                c=c2;
            }


            //count char for c
            currDnum++;

            if(i==s.length()-1){
                result+=Math.min(preDnum, currDnum);
            }
        }

        return result;
    }
}
