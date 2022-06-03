package com.mine.graph.undirected.bfs.wordladder;

import java.util.*;

public class WordLadder_wildcard {
    /**
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
     *
     *     Every adjacent pair of words differs by a single letter.
     *     Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     *     sk == endWord
     *
     * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
     *
     *
     *
     * Example 1:
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
     *
     * Example 2:
     *
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * Output: 0
     * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
     *
     *
     *
     * Constraints:
     *
     *     1 <= beginWord.length <= 10
     *     endWord.length == beginWord.length
     *     1 <= wordList.length <= 5000
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
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
            1. All the words in wordList are unique.
            2. 1 <= beginWord.length <= 10
            3. wordList[i].length == beginWord.length
         */

        //adjacent list
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        if(!wordSet.contains(endWord)){
            return 0;
        }

        Map<String, List<String>> adjList = new HashMap<>();
        for(String word : wordSet){//O(n)
            //for each word, * for any char
            char[] c = word.toCharArray();
            for(int i=0;i<word.length();i++) {
                char tmp = c[i];
                c[i] = '*';
                String pattern = String.valueOf(c);
                List<String> neighbours = adjList.getOrDefault(pattern, new ArrayList<>());
                neighbours.add(word);
                adjList.putIfAbsent(pattern, neighbours);
                c[i] = tmp;
            }
        }
        //Total = O(m^2*n*26) = O(m^2*n)

        //bfs
        int level = 0;
        Queue<String> q = new LinkedList<>();
        Set<String> visit = new HashSet<>();
        q.offer(beginWord);
        visit.add(beginWord);
        while(!q.isEmpty()){//O(n)
            level++;
            int n = q.size();
            for(int i=0;i<n;i++) {
                String vertex = q.poll();
                if(endWord.equals(vertex)){
                    return level;
                }

                //for each vertex: * for any char
                char[] c = vertex.toCharArray();
                for(int j=0;j<vertex.length();j++){
                    char tmp = c[j];
                    c[j] = '*';
                    for(String neighbour:adjList.get(String.valueOf(c))){
                        if(!visit.contains(neighbour)){
                            q.offer(neighbour);
                            visit.add(neighbour);
                        }
                    }
                    c[j] = tmp;
                }
            }
        }
        //total: O(n^2)

        //total: O(m^2*n) + O(n^2) = O(m^2*n)

        return 0;
    }
}
