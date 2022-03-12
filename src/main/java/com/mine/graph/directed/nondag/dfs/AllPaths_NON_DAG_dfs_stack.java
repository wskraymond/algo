package com.mine.graph.directed.nondag.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AllPaths_NON_DAG_dfs_stack {
    /**
     * https://www.geeksforgeeks.org/find-paths-given-source-destination/
     *
     * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’,
     * print all paths from given ‘s’ to ‘d’.
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPaths(int s, int d, int[][] graph) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        path.add(s);
        dfs(s, d, graph, new HashSet(),path, result);
        return result;
    }

    private void dfs(int s, int d, int[][] graph , Set<Integer> visit, List<Integer> path, List<List<Integer>> result){
        /**
         * https://leetcode.com/discuss/general-discussion/136503/what-is-difference-between-backtracking-and-depth-first-search
         *
         * Difference 1:
         * DFS handles an explicit tree.While Backtracking handles an implicit tree
         *
         * Difference 2:
         * Depth First Search is a special type of backtracking algorithmic design paradigm
         * where the process of backtracking takes place in the leaf nodes.
         * In case of backtracking,the algorithm also rejects the useless branch of the state-space tree.
         * This is why DFS maintains the entire tree structure while Backtracking maintaines a pruned tree
         *
         * https://stackoverflow.com/questions/1294720/whats-the-difference-between-backtracking-and-depth-first-search/17891070
         *
         *
         */
        if(s==d){
            result.add(new LinkedList<>(path)); //clone
            return;
        }

        //instead, boolean[] can be used (0...n index for vertex)
        visit.add(s);

        for(int adjacentV : graph[s]){
            if(visit.contains(adjacentV)){
                continue;
            }

            path.add(adjacentV);
            dfs(adjacentV, d, graph, visit, path, result);
            //remove last vertex
            path.remove(path.size()-1);
        }

        visit.remove(s);
    }
}
