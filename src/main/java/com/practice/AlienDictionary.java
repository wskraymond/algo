package com.practice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        return null;
    }

    private Map<Character,Set<Character>> edging(String[] words){
        return null;
    }

    private String topSort(Map<Character,Set<Character>> adjList){
        /*
            1. reversed post-order traversal
            2. cycle detection: while(not yet discovered), grey, black(finished)
         */
        return null;
    }

    private boolean dfs(Character v,
                        Map<Character,Set<Character>> adjList,
                        Map<Character,Character> color,
                        StringBuilder s){
        return false;
    }

}
