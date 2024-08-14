package com.mine.graph.undirected.unionfind;

import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCountComponents {
    private static CountComponents sol = new CountComponents();
    @Test
    public void testCase1(){
        assertEquals(2, sol.countComponents(6, new int[][]{{0,1}, {1,2}, {2,3}, {4,5}
        }));

        assertEquals(1, sol.countComponents(5, new int[][]{{0,1}, {1,2}, {2,0}, {3,0},{3,1},{3,2},
                {4,0},{4,1},{4,2},{4,3}
        }));

        assertEquals(3, sol.countComponents(6, new int[][]{{0,1}, {3,2},{4,5}
        }));

        assertEquals(1, sol.countComponents(3, new int[][]{{0,1}, {0,2}
        }));
    }
}
