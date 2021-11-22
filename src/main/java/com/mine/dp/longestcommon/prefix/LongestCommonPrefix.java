package com.mine.dp.longestcommon.prefix;

import java.util.Arrays;

public class LongestCommonPrefix {
    /**
     * https://leetcode.com/problems/longest-common-prefix/solution/
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int min =Integer.MAX_VALUE;
        for(String str:strs){
            min = Math.min(min, str.length());
        }

        StringBuilder result = new StringBuilder();
        boolean commonChar = true;
        for(int i=0;i<min && commonChar;i++){
            for(int j=1;j<strs.length && commonChar;j++){
                commonChar &= strs[j].charAt(i)==strs[j-1].charAt(i);
            }

            if(commonChar){
                result.append(strs[0].charAt(i));
            }
        }

        return result.toString();
    }
}
