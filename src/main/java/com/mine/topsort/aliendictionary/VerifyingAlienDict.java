package com.mine.topsort.aliendictionary;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VerifyingAlienDict {
    /**
     * https://leetcode.com/problems/verifying-an-alien-dictionary/description/
     *
     * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
     *
     * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     * Example 2:
     *
     * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * Output: false
     * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
     * Example 3:
     *
     * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
     * Output: false
     * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
     *
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * All characters in words[i] and order are English lowercase letters.
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        /*
            1. Given two different words of the same length, say a = a1a2...ak and b = b1b2...bk, the order of the two words depends on the alphabetic order of the symbols in the first place i where the two words differ (counting from the beginning of the words):
                Order: word1 < word2
                    if and only if word1[i] < word2[i] in the underlying order of the alphabet A.
            2. If two words have different lengths, the usual lexicographical order pads the shorter one with "blanks" (a special symbol that is treated as smaller than every element of A) at the end until the words are the same length, and then the words are compared as in the previous case.
                Order: word1 < word2
                    if and only if len(word1) < len(word2) and word1[:minLen] == word2[:minLen]
         */

        Map<Character, Integer> m = IntStream.range(0, order.length()).boxed()
                .collect(Collectors.toMap(i->order.charAt(i), Function.identity()));
        for(int i=0;i<words.length-1;i++){
            String word1 = words[i], word2 = words[i+1];
            int minLen = Math.min(word1.length(), word2.length());
            for(int j=0;j<minLen;j++){
                char c1 = word1.charAt(j), c2 = word2.charAt(j);
                if(c1!=c2){
                    if(m.get(c1)>m.get(c2)){
                        return false;   //rule 1
                    }

                    break;
                }

                if(j==minLen-1
                    && word1.length()> word2.length()){
                        return false;   //rule 2
                }
            }
        }
        return true;
    }
}
