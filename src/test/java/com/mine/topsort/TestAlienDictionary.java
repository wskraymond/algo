package com.mine.topsort;

import com.mine.topsort.aliendictionary.AlienDictionary;
import com.mine.topsort.aliendictionary.AlienDictionary_with_init;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAlienDictionary {
//    private static AlienDictionary sol = new AlienDictionary();

    private static AlienDictionary_with_init sol = new AlienDictionary_with_init();
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
    }
}
