package com.mine.topsort.aliendictionary.dfs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlienDictionary_dfs {
    /**
     * There is a new alien language which uses the latin alphabet.
     * However, the order among letters are unknown to you.
     * You receive a list of non-empty words from the dictionary,
     * where words are sorted lexicographically by the rules of this new language.
     * Derive the order of letters in this language.
     *
     * Example 1:
     *
     * Input:
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     *
     * Output: "wertf"
     * Example 2:
     *
     * Input:
     * [
     *   "z",
     *   "x"
     * ]
     *
     * Output: "zx"
     * Example 3:
     *
     * Input:
     * [
     *   "z",
     *   "x",
     *   "z"
     * ]
     *
     * Output: ""
     *
     * Explanation: The order is invalid, so return "".
     * Note:
     *
     * You may assume all letters are in lowercase.
     * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
     * If the order is invalid, return an empty string.
     * There may be multiple valid order of letters, return any one of them is fine.
     *
     */
    public String alienOrder(String[] words) {
        if(words.length==0){
            return "";
        }

        Map<Character,Set<Character>> adjList = edging(words);
        return topSort(adjList);
    }

    private Map<Character,Set<Character>> edging(String[] words){
        Map<Character,Set<Character>> adjList = Stream.of(words)
                .flatMap(w->w.chars()
                        .mapToObj(i->(char)i))
                .distinct()
                .collect(Collectors.toMap(Function.identity(),
                        c->new HashSet()));

        for(int i=0;i< words.length-1;i++){
            String w1=words[i], w2=words[i+1];
            int minLen = Math.min(w1.length(), w2.length());
            for(int j=0;j<minLen;j++){
                char c1 = w1.charAt(j), c2 =w2.charAt(j);
                if(c1!=c2){
                    adjList.get(c1).add(c2);
                    break;
                }

                if(j==minLen-1
                    && w1.length() > w2.length()){
                    return Collections.emptyMap(); //breach of rule 2
                }
            }
        }

        return adjList;
    }

    private String topSort(Map<Character,Set<Character>> adjList){
        /*
            1. reversed post-order traversal
            2. cycle detection: while(not yet discovered), grey, black(finished)
         */
        if(adjList.isEmpty()){
            return "";
        }

        StringBuilder s = new StringBuilder();
        Set<Character> vertices = adjList.keySet();
        Map<Character,Character> color = vertices
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                        c->'W'));

        for(Character v:vertices){
            if(color.get(v)=='W'){
                if(dfs(v, adjList, color, s)){
                    return "";
                }
            }
        }

        return s.reverse().toString(); //reversed
    }

    private boolean dfs(Character v,
                        Map<Character,Set<Character>> adjList,
                        Map<Character,Character> color,
                        StringBuilder s){
        color.put(v, 'G');
        for(Character n:adjList.get(v)){
            char c = color.get(n);
            if(c=='W'){
                if(dfs(n, adjList, color, s)){
                    return true;
                }
            } else if(c=='G'){
                return true;
            }
        }

        color.put(v, 'B');
        s.append(v); //post-order
        return false;
    }

}
