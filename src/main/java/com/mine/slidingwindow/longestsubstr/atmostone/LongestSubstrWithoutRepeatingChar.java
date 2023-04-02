package com.mine.slidingwindow.longestsubstr.atmostone;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> m = new HashMap<Character,Integer>();
        int i = 0;
        int max = 0;
        for(int j=i; j<s.length(); ++j){
            if(m.containsKey(s.charAt(j))
                    && m.get(s.charAt(j))>=i)
            {//duplicate
                max = Math.max(j-i, max);
                i = m.get(s.charAt(j)) + 1; //does not include preceeding char of j element
            } else if(j==s.length()){ //no duplicate, include j
                max = Math.max(j-i+1, max);
            }
            m.put(s.charAt(j), j);
        }
        return max;
    }
}
