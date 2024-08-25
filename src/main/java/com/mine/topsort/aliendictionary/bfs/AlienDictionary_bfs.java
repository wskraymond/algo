package com.mine.topsort.aliendictionary.bfs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AlienDictionary_bfs {
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
        return bfs(adjList);
    }

    private String bfs(Map<Character,Set<Character>> adjList){
        StringBuilder result = new StringBuilder("");
        Map<Character, Long> inDegree = adjList.values().stream().flatMap(Set::stream).collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        Queue<Character> q = new LinkedList<>();
        adjList.keySet().stream().filter(c->!inDegree.containsKey(c)).forEach(q::offer);
        while(!q.isEmpty()){
            char c = q.poll();
            result.append(c);
            for(char neighbour:adjList.get(c)){
                inDegree.compute(neighbour, (key,o)->o-1);
                if(inDegree.get(neighbour)==0L){
                    q.offer(neighbour);
                    inDegree.remove(neighbour);
                }
            }
        }

        return inDegree.isEmpty() ? result.toString() : "";
    }

    private Map<Character,Set<Character>> edging(String[] words){
        Map<Character,Set<Character>> adjList = new HashMap<>();
        Arrays.stream(words).flatMap(w->w.chars()
                .mapToObj(i->(char)i))
                .collect(Collectors.toSet())
                .forEach(c->adjList.computeIfAbsent(c,
                        HashSet::new));

        for(int i=0;i<words.length-1;i++){
            String w1 = words[i], w2 = words[i+1];
            int minLen = Math.min(w1.length(), w2.length());
            for(int j=0;j<minLen;j++){
                char c1=w1.charAt(j), c2=w2.charAt(j);
                if(c1!=c2){
                    adjList.get(c1).add(c2);
                    break;
                }

                if(j==minLen-1
                    && w1.length()>w2.length()){
                    return Collections.emptyMap();
                }
            }
        }

        return adjList;
    }
}
