package com.mine.graph.directed.nondag.dfs.leadstodestination;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LeadsToDestination_misunderstood {
    /**
     * https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
     *
     * Given the edges of a directed graph where edges[i] = [ai, bi]
     * indicates there is an edge between nodes ai and bi,
     * and two nodes source and destination of this graph,
     *
     * determine whether or not all paths starting from source eventually, end at destination,
     * that is:
     *     At least one path exists from the source node to the destination node
     *     If a path exists from the source node to a node with no outgoing edges,
     *          then that node is equal to destination.
     *     The number of possible paths from source to destination is a finite number.
     *
     * Return true if and only if all roads from source lead to destination.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
     * Output: false
     * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
     *
     * Example 2:
     *
     * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
     * Output: false
     * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
     *
     * Example 3:
     *
     * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
     * Output: true
     *
     *
     *
     * Constraints:
     *
     *     1 <= n <= 104
     *     0 <= edges.length <= 104
     *     edges.length == 2
     *     0 <= ai, bi <= n - 1
     *     0 <= source <= n - 1
     *     0 <= destination <= n - 1
     *     The given graph may have self-loops and parallel edges.
     *
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        /*
            1. vertex without outgoing edges:
                a) edges[vertex] = null
                b) edges.length != n
                c) when checking with edges[i] from 0 to n-1 , we have to make sure i < edges.length

             2.  edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
                a) be careful again !!!!!!!!!!
                b) required to build up an adjacency list.......
         */

        if(destination < edges.length && edges[destination]!=null && edges[destination].length!=0){
            return false;
        }

        boolean hasNonDestLeaf = IntStream.range(0, n)
                .anyMatch(i-> i < edges.length
                    && edges[i]!=null
                    && edges[i].length==0
                    && i!=destination);

        if(hasNonDestLeaf){
            return false;
        }

        char[] color = new char[n];
        Arrays.fill(color, 'W');
        boolean noCycles =  dfs(source, edges, color);
        return noCycles;
    }

    public boolean dfs(int vertex, int[][] edges, char[] color){
        color[vertex] = 'G';

        boolean noCycles = true;
        if(edges[vertex]!=null) {
            for (int neighbour : edges[vertex]) {
                if (!noCycles) {
                    break;
                }

                if (color[neighbour] == 'W') {
                    noCycles = dfs(neighbour, edges, color);
                } else if (color[neighbour] == 'G') {
                    noCycles = false; //it is a cycle
                }
            }
        }

        color[vertex]='B';
        return noCycles;
    }
}
