package com.mine.design.LRU;

import java.util.LinkedHashMap;

/**
 * We're asked to implement the structure
 * which provides the following operations in O(1) time :
 *
 *     Get the key / Check if the key exists
 *
 *     Put the key
 *
 *     Delete the first added key
 *
 * The first two operations in O(1) time are provided by
 * the standard hashmap,
 * and the last one - by linked list.
 */
public class LRUCache_access_mode<K,V> extends LinkedHashMap<K,V> {
    private final int maxSize;

    public LRUCache_access_mode(int capacity) {
        this.maxSize = capacity;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }
}
