package com.mine.trie;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class Trie {
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    /*
        Comparison: Trie vs HashSet
        please refer to com.mine.trie.TestTrie.java
        Time Complexity:
        1. search:
            Set: best=O(1), worst=o(N)
            Trie: best=worst=O(k), K=size of the input word
        2. beginWith:
            Set: best=worst=o(N*K)
            Trie: best=worst=O(k), K=size of the input word
        Space Complexity:
            Set: len(w1 + w2 + w3 + ...)
            Trie:
                1. overhead: each node contains array in size=26
                2. space efficiency: common prefix stores once only

     */
    class TrieNode{
        //immutable : init with an array with size=26
        private final TrieNode[] children; //instead, hashmap can be used
        //mutable: a char could've already existed but becomes the end of word
        //          once a complete word is inserted, which is a prefix of an existing word added in dictionary
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; //consist only of lowercase English letters
            isEndOfWord = false;
        }
    }

    private final TrieNode root;
    public Trie() {
        /*
            1. A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store
            and retrieve keys in a dataset of strings.
            2. There are various applications of this data structure, such as autocomplete and spellchecker.
         */
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(curr.children[c-'a']==null){
                curr.children[c-'a']=new TrieNode();
            }
            curr = curr.children[c-'a'];
        }

        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = find(word);
        return node!=null ? node.isEndOfWord : false;
    }

    private TrieNode find(String word){
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(curr.children[c-'a']==null){
                return null;
            }
            curr = curr.children[c-'a'];
        }

        return curr;
    }

    public boolean startsWith(String prefix) {
        return find(prefix)!=null;
    }
}
