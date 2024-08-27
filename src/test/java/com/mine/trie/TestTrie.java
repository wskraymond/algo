package com.mine.trie;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestTrie {

    @Test
    public void testSearch_set(){
        Set<String> sol = new HashSet();
        sol.add("apple");
        assertTrue(sol.contains("apple"));
        assertFalse(sol.contains("app"));
        assertFalse(sol.contains("baby"));
        sol.add("app");
        assertTrue(sol.contains("app"));
    }

    @Test
    public void testSearch_trie(){
        Trie sol = new Trie();
        sol.insert("apple");
        assertTrue(sol.search("apple"));
        assertFalse(sol.search("app"));
        assertFalse(sol.search("baby"));
        sol.insert("app");
        assertTrue(sol.search("app"));
    }

    @Test
    public void testBeginWith_trie(){
        Trie sol = new Trie();
        sol.insert("apple");
        assertTrue(sol.startsWith("app"));
        sol.insert("app");
        assertTrue(sol.startsWith("app"));
    }

    @Test
    public void testBeginWith_set(){
        Set<String> sol = new HashSet();
        sol.add("apple");
        sol.add("app");
        assertTrue(sol.stream().anyMatch(w-> w.startsWith("app")));
    }

    @Test
    public void testMultiPrefix_trie(){
        Trie sol = new Trie();
        Stream.of("apple", "axe","baby", "ban", "banana", "cat", "microsoft")
                .peek(sol::insert)
                .forEach(w->assertTrue(sol.search(w)));
        assertTrue(sol.startsWith("banan"));
    }

    @Test
    public void testMultiPrefix_set(){
        Set<String> sol = new HashSet();
        Stream.of("apple", "axe","baby", "ban", "banana", "cat", "microsoft")
                .peek(sol::add)
                .forEach(w->assertTrue(sol.contains(w)));
        assertTrue(sol.stream().anyMatch(w-> w.startsWith("banan")));
    }
}
