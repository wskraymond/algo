package com.mine.graph.dfs;

import java.util.List;

public class AllPathsFromSourceToTarget_DAG_ONLY_dfs {
    /**
     *
     *


     Example 1:


     Input: graph = [[1,2],[3],[3],[]]
     Output: [[0,1,3],[0,2,3]]
     Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
     Example 2:


     Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
     Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
     Example 3:

     Input: graph = [[1],[]]
     Output: [[0,1]]
     Example 4:

     Input: graph = [[1,2,3],[2],[3],[]]
     Output: [[0,1,2,3],[0,2,3],[0,3]]
     Example 5:

     Input: graph = [[1,3],[2],[3],[]]
     Output: [[0,1,2,3],[0,3]]


     Constraints:

     n == graph.length
     2 <= n <= 15
     0 <= graph[i][j] < n
     graph[i][j] != i (i.e., there will be no self-loops).
     All the elements of graph[i] are unique.
     The input graph is guaranteed to be a DAG.
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return null;
    }
}
