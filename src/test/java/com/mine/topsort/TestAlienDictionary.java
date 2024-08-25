package com.mine.topsort;

import com.mine.topsort.aliendictionary.bfs.AlienDictionary_bfs;
import com.mine.topsort.aliendictionary.bfs.old.AlienDictionary_with_init_missing_len_check;
import com.mine.topsort.aliendictionary.dfs.AlienDictionary_dfs;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAlienDictionary {

    private static AlienDictionary_bfs sol = new AlienDictionary_bfs();
    private static AlienDictionary_dfs sol2 = new AlienDictionary_dfs();

    @Test
    public void testCase1(){
        assertEquals("wertf", sol.alienOrder(new String[] {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        }));

        assertEquals("hernf", sol.alienOrder(new String[] {
                "hrn","hrf","er","enn","rfnn"
        }));

        assertEquals("zx", sol.alienOrder(new String[] {
                "z","x"
        }));

        assertEquals("", sol.alienOrder(new String[] {
                "z","x", "z"
        }));

        assertEquals("", sol.alienOrder(new String[] {
                "apple", "app"
        }));
    }

    @Test
    public void testCase2(){
        assertEquals("wertf", sol2.alienOrder(new String[] {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        }));

        assertEquals("hernf", sol2.alienOrder(new String[] {
                "hrn","hrf","er","enn","rfnn"
        }));

        assertEquals("zx", sol2.alienOrder(new String[] {
                "z","x"
        }));

        assertEquals("", sol2.alienOrder(new String[] {
                "z","x", "z"
        }));

        assertEquals("", sol2.alienOrder(new String[] {
                "apple", "app"
        }));
    }
}
