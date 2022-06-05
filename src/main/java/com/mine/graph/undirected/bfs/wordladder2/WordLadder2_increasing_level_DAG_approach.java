package com.mine.graph.undirected.bfs.wordladder2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordLadder2_increasing_level_DAG_approach {
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
            1. return "all" the shortest transformation sequences from beginWord to endWord
            2. Constraint: beginWord != endWord
            3. equals/compareTo , does not work for String vs StringBuilder vs char[]
                    i) trick: filter same char at index i
                    ii) or clone String every time for comparison
            4  String is immutable, you can use toCharArray() or StringBuilder for modification
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
                .map(StringBuilder::new)
                .collect(Collectors.toMap(
                    StringBuilder::toString,
                    w->IntStream.range(0, w.length())
                                .mapToObj(i->IntStream.rangeClosed('a', 'z')
                                        .filter(c->c!=w.charAt(i)) //filter out nextW equals current word
                                        .mapToObj(c->{
                                            char tmp = w.charAt(i);
                                            w.setCharAt(i, (char) c);
                                            String nextW = w.toString();
                                            w.setCharAt(i, tmp);
                                            return nextW;})
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
        int level = 0;
        Map<String, Integer> visit = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        visit.put(beginWord, level+1);
        while(!q.isEmpty()){
            level++;
            int n = q.size();
            for(int i=0;i<n;i++) {
                String vertex = q.poll();

                List<String> toBeRemoved = new LinkedList<>();
                for(String neighbour : adjList.getOrDefault(vertex,Collections.emptySet())){
                    if(!visit.containsKey(neighbour)){ //never visited
                        visit.put(neighbour, level + 1);
                        q.add(neighbour);
                    } else if(visit.get(neighbour) <= level){ //visited and equal or less than current level
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

        //DFS - traverse DAG
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, adjList, path, result);

        return result;
    }

    private void dfs(String vertex, String endWord,
                     Map<String,Set<String>> adjList, List<String> path,
                     List<List<String>> result){
        if(vertex.equals(endWord)){
            result.add(new ArrayList<>(path));
            return;
        }

        for(String neighbour:adjList.getOrDefault(vertex, Collections.emptySet())){
            path.add(neighbour);
            dfs(neighbour, endWord, adjList, path, result);
            path.remove(neighbour); //backtrack
        }
    }
}
