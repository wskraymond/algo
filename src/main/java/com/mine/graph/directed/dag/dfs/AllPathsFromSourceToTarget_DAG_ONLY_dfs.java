package com.mine.graph.directed.dag.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AllPathsFromSourceToTarget_DAG_ONLY_dfs {
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
        if(graph==null || graph.length==0){
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path, result, graph);
        return result;
    }

    public void dfs(int currentVertex,List<Integer> path, List<List<Integer>> result, int[][] graph){
        if(currentVertex==graph.length-1){
            result.add(new ArrayList<>(path)); //clone
        }

        for(int vertex : graph[currentVertex]){
            path.add(vertex);
            dfs(vertex, path, result, graph);
            path.remove(path.size()-1);
        }
    }
}
