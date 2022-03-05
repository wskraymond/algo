package com.mine.graph.directed.dag.bfs;

import java.sql.PreparedStatement;
import java.util.*;

public class AllPathsFromSourceToTarget_DAG_ONLY_bfs {
    /**
     * https://leetcode.com/problems/all-paths-from-source-to-target/
     *
     Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
     find all possible paths from node 0 to node n - 1 and return them in any order.

     The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

     Example 1:

     Input: graph = [[1,2],[3],[3],[]]
     Output: [[0,1,3],[0,2,3]]
     Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

     Example 2:

     Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
     Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]



     Constraints:

     n == graph.length
     2 <= n <= 15
     0 <= graph[i][j] < n
     graph[i][j] != i (i.e., there will be no self-loops).
     All the elements of graph[i] are unique.
     The input graph is guaranteed to be a DAG.
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /*
        Thanks to the nature of DAG, then we can use BFS to solve it
            - Directed Graph here
                - Thus, we don’t have to mark “visited” here

         But if it is undirected, mark as visited is required to avoid re-visiting
            - a vertex marked as visited previously was revisited later by another vertex (it means longer path) will not be processed.
                - which can be used to solve shortest path problem
                - But this nature here will not help to solve all path problem.....?????
         */

        if(graph==null || graph.length==0){
            return Collections.emptyList();
        }

        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(Arrays.asList(0)); //add src

        while(!q.isEmpty()){
            List<Integer> path = q.poll();
            int dest = path.get(path.size()-1);
            if(dest==graph.length-1){
                // (here to add to result)
                // edge cases: only one vertex and no edges
                paths.add(path);
            } else {
                for (int vertex : graph[dest]) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(vertex);
                    q.offer(newPath);
                }
            }
        }
        return paths;
    }
}
