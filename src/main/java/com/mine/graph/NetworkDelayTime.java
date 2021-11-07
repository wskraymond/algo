package com.mine.graph;

public class NetworkDelayTime {
    /**
     * You are given a network of n nodes,
     *  - labeled from 1 to n.
     * You are also given times,
     *  - a list of travel times as directed edges times[i] = (ui, vi, wi),
     *  - where ui is the source node,
     *          vi is the target node,
     *      and wi is the time it takes
     * for a signal to travel from source to target.
     *
     * We will send a signal from a given node k.
     *  - Return the time it takes for all the n nodes
     *      - to receive the signal.
     * If it is impossible for all the n nodes to receive the signal,
     *  - return -1.
     *
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * Output: 2
     * Example 2:
     *
     * Input: times = [[1,2,1]], n = 2, k = 1
     * Output: 1
     * Example 3:
     *
     * Input: times = [[1,2,1]], n = 2, k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= k <= n <= 100
     * 1 <= times.length <= 6000
     * times[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 0 <= wi <= 100
     * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
     *
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        /**
         * src = k
         * vertexIndex: i (1...n)
         * path distance(not yet confirmed that it is shortest) from s to i: dist(i)
         * Graph:
         *  - from j to all connected i with cost wji
         *  - Map{j,List<[i, wji]> or vertex[j] = List<[i, wji]>
         * Min Heap:
         *  - connected vertices{path distance}
         *  - for greedy choice
         * Recursion Relations:
         *  - dist[i] = min{heap} + w(j,i) | given vertex(j) of min{heap} is the locally optimal choice
         *  - And given that w(j,i) >= 0
         * Visited And/Or Shortest Distance(sd):
         *  - boolean[] visited
         *      - visited[i] = true
         *  - sd[i] = min{heap} | given vertex(i) of min{heap} is the global optimal value
         *      - if(sd[i]!=Integer.MAX_VALUE)
         * Our goal:
         *   Max{sd}
         */

    }
}
