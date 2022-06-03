package com.mine.graph.directed.nondag.dfs.leadstodestination;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeadsToDestination_miss_edges_from_src_with_indegree {
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
            1. vertex without outgoing edges

            2.  edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
                a) be careful again !!!!!!!!!!
                b) required to build up an adjacency list.......

            3. vertex without outgoing edges must be reachable from source ,
                it means if there is a sub-graph points to source but unreachable from source,
                vertex in this subgraph can have no outgoing edges
         */

        Map<Integer, List<Integer>> adjList = Arrays.stream(edges)
                .collect(Collectors.groupingBy(edge->edge[0],
                         Collectors.mapping(edge->edge[1], Collectors.toList())
                ));

        if(adjList.containsKey(destination)){
            return false;
        }

        boolean hasNonDestLeaf = IntStream.range(0, n)
                .anyMatch(i-> i!=destination
                        && !adjList.containsKey(i));

        if(hasNonDestLeaf){
            return false;
        }

        char[] color = new char[n];
        Arrays.fill(color, 'W');
        boolean noCycles =  dfs(source, adjList, color);
        return noCycles;
    }

    public boolean dfs(int vertex, Map<Integer, List<Integer>> adjList, char[] color){
        color[vertex] = 'G';

        boolean noCycles = true;
        for (int neighbour : adjList.getOrDefault(vertex, Collections.emptyList())) {
            if (!noCycles) {
                break;
            }

            if (color[neighbour] == 'W') {
                noCycles = dfs(neighbour, adjList, color);
            } else if (color[neighbour] == 'G') {
                noCycles = false; //it is a cycle
            }
        }

        color[vertex]='B';
        return noCycles;
    }
}
