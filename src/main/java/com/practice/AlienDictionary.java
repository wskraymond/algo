package com.practice;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlienDictionary {
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
        Map<Character,Set<Character>> adjList = edging(words);
        return topSort(adjList);
    }

    private Map<Character,Set<Character>> edging(String[] words){
        //init
        Map<Character, Set<Character>> adjList = Arrays.stream(words)
                .flatMap(w-> w.chars().mapToObj(i->(char)i))
                .distinct()
                .collect(Collectors.toMap(Function.identity(), HashSet::new));

        for(int i=0;i<words.length-1;i++){
            String w=words[i], w2=words[i+1];
            int minL = Math.min(w.length(), w2.length());
            for(int j=0;j<minL;j++){
                if(w.charAt(j)!=w2.charAt(j)){
                    adjList.get(w.charAt(j)).add(w2.charAt(j));
                    break;
                }
            }
        }
        return adjList;
    }

    private String topSort(Map<Character,Set<Character>> adjList){
        if(adjList.isEmpty()){
            return "";
        }
        /*
            1. reversed post-order traversal
            2. cycle detection: while(not yet discovered), grey, black(finished)
         */
        Map<Character, Character> colors = adjList.keySet().stream().collect(Collectors.toMap(Function.identity(), c->'W'));
        StringBuilder result = new StringBuilder("");
        for(Character vertex : adjList.keySet()){
            if(colors.get(vertex)=='W'){
                if(dfs(vertex, adjList,colors, result)){
                    return "";
                }
            }
        }
        return result.reverse().toString();
    }

    private boolean dfs(Character v,
                        Map<Character,Set<Character>> adjList,
                        Map<Character,Character> colors,
                        StringBuilder s){
        colors.put(v, 'G');
        for(Character n : adjList.get(v)){
            if(colors.get(n)=='W'){
                if(dfs(v, adjList,colors,s)){
                    return true;
                }
            } else if(colors.get(n)=='G') {
                return true;
            }
        }
        colors.put(v,'B');
        s.append(v);
        return false;
    }

}
