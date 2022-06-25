package com.mine.graph.undirected.bfs.wordladder2.success;

import java.util.*;

public class WordLadder2_increasing_level_DAG_approach_by_buildling_DAG {
    /**
     * https://leetcode.com/problems/word-ladder-ii/solution/
     *
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
     *
     *     Every adjacent pair of words differs by a single letter.
     *     Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     *     sk == endWord
     *
     * Given two words,
     *  beginWord and endWord,
     *  and a dictionary wordList,
     *
     * return "all" the shortest
     *      transformation sequences from beginWord to endWord,
     *   or an empty list if no such sequence exists.
     *
     * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
     *
     *
     *
     * Example 1:
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
     * Explanation: There are 2 shortest transformation sequences:
     * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
     * "hit" -> "hot" -> "lot" -> "log" -> "cog"
     *
     * Example 2:
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * Output: []
     * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
     *
     *
     *
     * Constraints:
     *
     *     1 <= beginWord.length <= 5
     *     endWord.length == beginWord.length
     *     1 <= wordList.length <= 1000
     *     wordList[i].length == beginWord.length
     *     beginWord, endWord, and wordList[i] consist of lowercase English letters.
     *     beginWord != endWord
     *     All the words in wordList are unique.
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        /*
            1. BFS: Removing the newly visited neighbour of the current layer's vertex from the wordSet
                        - instead of simplifying the undirected graph

            2. DFS: every path from src to dst must be the shortest path.
                        - There is no need to compare the length of the path each time we reach the endWord.
         */
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)){
            return Collections.emptyList();
        }

        Map<String, List<String>> adjList = bfs(beginWord,endWord, wordSet);
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs();

        return paths;
    }

    private List<String> findLevelIncreasingNeighbours(String vertex, Set<String> wordSet){
        return null;
    }

    private Map<String, List<String>> bfs(String beginWord, String endWord, Set<String> wordSet){
        /*
            Note that beginWord does not need to be in wordList.
                - wordList might or might not have beginWord
         */
        // remove the root word which is the first layer in the BFS
        if(wordSet.contains(beginWord)) {
            wordSet.remove(beginWord);
        }

        //Build a level-increasing-Only DAG
        Map<String,List<String>> adjList = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);

        while(!q.isEmpty()){
            int n = q.size();
            Set<String> toBeRemoved = new HashSet<>();
            for(int i=0;i<n;i++){
                String vertex = q.poll();

                for(String neighbour: findLevelIncreasingNeighbours(vertex, wordSet)){
                    /*
                    The reason why we don't remove it immediately from wordSet,
                    becos other vertex in current layer might point to it,
                    and they're also one of the valid edges
                     */
                    toBeRemoved.add(neighbour);

                    //make a valid edge
                    adjList.compute(vertex, (k,v)->{
                        List<String> l = v!=null ? v : new ArrayList<>();
                        l.add(neighbour);
                        return l;
                    });

                    if(!visited.contains(neighbour)){
                        //other vertex in current layer might also point to it
                        visited.add(neighbour);
                        q.offer(neighbour);
                    }
                }

                //clear all visited neighbours in current layer
                toBeRemoved.stream().forEach(e->{
                    if(wordSet.contains(e)){
                        wordSet.remove(e);
                    }
                });
            }
        }

        return adjList;
    }

    private void dfs(){

    }
}
