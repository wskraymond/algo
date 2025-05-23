package com.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        final int n = beginWord.length();
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        if(!wordSet.contains(endWord)){
            return 0;
        }

        Map<String, Set<String>> adjList = new HashMap<>();
        for(String word:wordSet){
            char[] tmp = word.toCharArray();
            for(int i=0;i<n;i++){
                char c = tmp[i];
                tmp[i] = '*';
                String pattern = String.valueOf(tmp);
                Set<String> l = adjList.getOrDefault(pattern, new HashSet<>());
                l.add(word);
                adjList.putIfAbsent(pattern, l);
                tmp[i] = c;
            }
        }

        //bfs
        int level=0;
        Queue<String> q = new LinkedList<>();
        Set<String> visit = new HashSet<>();
        visit.add(beginWord);
        q.offer(beginWord);
        while(!q.isEmpty()){
            level++;
            final int s = q.size();
            for(int i=0;i<s;i++){
                String word = q.poll();
                if(word.equals(endWord)){
                    return level;
                }
                char[] w = word.toCharArray();
                for(int j=0;j<n;j++){
                    char c = w[j];
                    w[j]='*';
                    String pattern = String.valueOf(w);
                    for(String neighbour:adjList.get(pattern)){
                        if(!visit.contains(neighbour)){
                            q.offer(neighbour);
                            visit.add(neighbour);
                        }
                    }
                    w[j]=c;
                }
            }
        }

        return 0;
    }
}
