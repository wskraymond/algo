package com.mine.graph.directed.dag.dfs;

import java.util.*;

public class AllPathsFromSourceToTarget_DAG_ONLY_dfs_stack {
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
        Deque<List<Integer>> pathStack = new ArrayDeque<>();
        pathStack.push(Arrays.asList(0));
        while(!pathStack.isEmpty()){
            List<Integer> path = pathStack.pop();
            Integer last = path.get(path.size()-1);
            if(last==graph.length-1){
                result.add(path);
            } else {
                for(int vertex : graph[last]){
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(vertex);
                    pathStack.push(newPath);
                }
            }
        }

        return result;
    }
}
