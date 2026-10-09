package com.practice.lc;

import java.util.*;

public class WordLadder {
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
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord) || beginWord.equals(endWord)){
            return 0;
        }

        //adjList Key: * at any single letter -> value: a list of words which belongs to the key with only one single letter difference
        //bi-directed and cyclic graph
        //Non-DAG
        Map<String, List<String>> adjList = new HashMap<>();
        for(String word:wordSet){
            char[] chars = word.toCharArray();
            for(int i=0;i<chars.length;i++){
                char tmp = chars[i];
                chars[i]='*';
                String key = String.valueOf(chars);
                adjList.computeIfAbsent(key, k->new ArrayList<>());
                adjList.get(key).add(word);
                chars[i]=tmp;
            }
        }

        //bfs for each * at any letter of a word as a key to find its neighbour
        //index counting
        Queue<String> q = new LinkedList<>();
        Set<String> visit = new HashSet<>();
        int level=0;
        q.offer(beginWord);
        visit.add(beginWord);
        while(!q.isEmpty()){
            level++;
            int n = q.size();
            for(int i=0;i<n;i++){
                String vertex = q.poll();
                if(vertex.equals(endWord)){
                    return level;
                }

                char[] chars = vertex.toCharArray();
                for(int j=0;j<chars.length;j++){
                    char tmp = chars[j];
                    chars[j]='*';
                    String key = String.valueOf(chars);
                    for(String neighbour : adjList.getOrDefault(key, Collections.emptyList())){
                        if(visit.contains(neighbour)){
                           continue;
                        }

                        visit.add(neighbour);
                        q.offer(neighbour);
                    }
                    chars[j]=tmp;
                }
            }
        }

        return 0;
    }
}
