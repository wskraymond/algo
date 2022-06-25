package com.mine.graph.undirected.bfs.wordladder2.failure;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordLadder2_misconception_increasing_level_DAG_approach {
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
        return "all" the shortest transformation sequences from beginWord to endWord
         */

        //O(1) searching
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        if(!wordSet.contains(endWord)){
            return Collections.emptyList();
        }

        //kinda NOT quite readable using nested stream
        //time-consuming to write accurate code in interview and debug
        /*
            usage of mapToObj, flatMap ,
            multi filter with reference method,
            IntStream for char
        */

        //undirected adjList
        Map<String, Set<String>> adjList = wordSet.stream()
                .map(String::toCharArray)
                .collect(Collectors.toMap(
                    String::valueOf,
                    w->IntStream.range(0, w.length)
                                .mapToObj(i->IntStream.rangeClosed('a', 'z')
                                        .mapToObj(c->{
                                            char tmp = w[i];
                                            w[i] = (char) c;
                                            String newWord = String.valueOf(w);
                                            w[i] = tmp;
                                            return newWord;})
                                        .filter(w::equals)
                                        .filter(wordSet::contains)
                                        .collect(Collectors.toSet()))
                                .flatMap(l->l.stream())
                                .collect(Collectors.toSet())));

        //BFS
        //simplified AdjList :  undirected to DAG
        /*
            1. Visited node can be still a valid edge for increasing level
               How ?
                   i) Map<String, Integer> should be used for visited instead of set
                   ii) remove the current words(vertex) from wordSet after finishing current level
                        (LeetCode Solution)

            2. the main idea of this approach is to simplify the AdjList(Undirected-> DAG)
                to make DSF traversing only valid edge for minimum
                    - Thus, we have to traverse all vertices in BFS (don't stop in endword)
         */
        Set<String> visit = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        visit.add(beginWord);
        int level = 0;
        boolean found = false;
        while(!found
            && !q.isEmpty()){
            level++;
            int n = q.size();
            for(int i=0;i<n;i++) {
                String vertex = q.poll();
                if(vertex.equals(endWord)){
                    found = true;
                    break;
                }

                List<String> toBeRemoved = new LinkedList<>();
                for(String neighbour : adjList.getOrDefault(vertex,Collections.emptySet())){
                    if(!visit.contains(neighbour)){
                        visit.add(neighbour);
                        q.add(neighbour);
                    } else {
                        toBeRemoved.add(neighbour);
                    }
                }

                //simplified to DAG
                adjList.computeIfPresent(vertex, (k,v)->{
                    toBeRemoved.stream().forEach(r->v.remove(r));
                    return v;
                });
            }
        }

        //DFS - backtracking DAG
        return Collections.emptyList();
    }
}
