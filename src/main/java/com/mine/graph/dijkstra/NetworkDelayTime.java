package com.mine.graph.dijkstra;

import java.util.*;

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
         *  - connected vertices{i, dist(i)}
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

        Map<Integer, List<int[]>> vertices = new HashMap<>((int)(n/0.75) + 1);
        for(int[] time : times){    //O(E)
            int from=time[0];
            int to=time[1];
            int cost=time[2];
            if(!vertices.containsKey(from)){
                vertices.put(from, new ArrayList<>());
            }

            List<int[]> edges = vertices.get(from);
            edges.add(new int[]{to, cost});
        }

        int[] sd = new int[n+1];
        Arrays.fill(sd, Integer.MAX_VALUE);     //O(V)

        Queue<int[]> pathDistQ = new PriorityQueue<>(Comparator.comparingInt(d -> d[1]));
        pathDistQ.add(new int[]{k, 0});
        while(!pathDistQ.isEmpty()){
            int[] pathDist = pathDistQ.poll();
            int vertex = pathDist[0];
            int dist = pathDist[1];

            if(sd[vertex]!=Integer.MAX_VALUE){ //if visited     //O(V)
                continue;
            }
            sd[vertex] = dist;

            if(vertices.containsKey(vertex)) {
                List<int[]> edges = vertices.get(vertex);
                for(int[] edge: edges){     //O(E)
                    int to = edge[0];
                    int cost = edge[1];
                    if(sd[to]!=Integer.MAX_VALUE) { //if visited
                        continue;
                    }

                    pathDistQ.add(new int[]{to, sd[vertex] + cost});    //O(logE)
                }
            }
        }

        int max=0;
        for(int i=1;i<=n;i++){
            max = Math.max(max, sd[i]);
        }
        return max!=Integer.MAX_VALUE ? max : -1;
    }
}
