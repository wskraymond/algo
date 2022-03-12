package com.mine.graph.directed.nondag.bfs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllPaths_NON_DAG_bfs {
    /**
     * https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/?ref=lbp
     *
     * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’,
     * print all paths from given ‘s’ to ‘d’.
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPaths(int s, int d, int[][] graph) {
        /**
         * utility function is required to check
         *      if current vertex is already present in path
         */
        if(graph==null || graph.length==0){
            return Collections.emptyList();
        }


        List<List<Integer>> result = new LinkedList<>();
        Queue<Set<Integer>> q = new LinkedList<>();
        q.add(Stream.of(s).collect(Collectors.toSet()));
        while(!q.isEmpty()){
            Set<Integer> traversed = q.poll();
            List<Integer> path = traversed.stream().collect(Collectors.toList());
            int last = path.get(path.size()-1);
            if(last == d){
                result.add(path);
            } else {
                for(int vertex:graph[last]){
                    if(!traversed.contains(vertex)){
                        Set<Integer> newTraversed = new LinkedHashSet<>(traversed);
                        newTraversed.add(vertex);
                        q.add(newTraversed);
                    }
                }
            }
        }

        return result;
    }
}
