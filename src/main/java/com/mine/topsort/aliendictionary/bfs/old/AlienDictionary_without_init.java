package com.mine.topsort.aliendictionary.bfs.old;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AlienDictionary_without_init {
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

        Map<Character,Set<Character>> adjList = new HashMap<>();
        final int n = words.length;
        dfs(0,0,n, n, words, adjList);
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

    private void dfs(int i, int start, int end, int n,
                     String[] words,
                     Map<Character,Set<Character>> adjList){
        Character c = null;
        for(int j=start, k=start; k<end; k++){
            if(i>=words[k].length()){
                j++;
                continue;
            }


            final Character next = words[k].charAt(i);
            if(c==null ){
                adjList.computeIfAbsent(next, HashSet::new);
            } else if (!next.equals(c)){
                adjList.computeIfAbsent(next, HashSet::new);
                adjList.get(c).add(next);
                dfs(i+1, j,k, n, words,adjList); //excluding current row
                j=k;
            } else if(k==end-1){ //end
                dfs(i+1, j,k+1, n, words,adjList); //including current row
            }

            c = next;
        }
    }
}
