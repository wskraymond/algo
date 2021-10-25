package com.mine.design.LRU;

import java.util.HashMap;

/**
 * Design a data structure that follows
 * the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 *     LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *     int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *     void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 *
 * Constraints:
 *
 *     1 <= capacity <= 3000
 *     0 <= key <= 104
 *     0 <= value <= 105
 *     At most 2 * 105 calls will be made to get and put.
 *
 */
public class LRUCache {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    static class Node{
        private int key;
        private int val;
        private Node prev, next;

        public Node() {
            this.key = 0;
            this.val = 0;
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * we need to have O(1) for removal of node (in the middle) of doubly linked list.
     */
//    Queue<Node> q = new LinkedList<>();
//    q.remove(node); //O(n) becos traversal

    private final Node head;
    private final int maxSize;
    private final HashMap<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.maxSize = capacity;
        this.cache = new HashMap<>((int)(this.maxSize/0.75) + 1);
        this.head = new Node();
        head.next = head;
        head.prev = head;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            Node n = cache.get(key);
            moveToHead(n);
            return n.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node n = cache.get(key);
            n.val = value;
            moveToHead(n);
        } else {
            Node newN = new Node(key, value);
            if(cache.size() >= maxSize){
                Node leastUsedNode = head.prev;
                remove(leastUsedNode);
                cache.remove(leastUsedNode.key);
            }

            addToHead(newN);
            cache.put(key, newN);
        }
    }

    private void remove(Node node){
        Node prevN = node.prev;
        Node nextN = node.next;
        prevN.next = nextN;
        nextN.prev = prevN;

        //GC ?
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node){
        Node nextN = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextN;
        nextN.prev = node;
    }

    private void moveToHead(Node node){
        remove(node);
        addToHead(node);
    }
}
