package com.mine.topsort.dfs;

import java.util.*;
import java.util.stream.Collectors;

public class CourseSchedule2_dfs_with_reversed_list {
    /**
     * There are a total of numCourses courses you have to take,
     * labeled from 0 to numCourses - 1.
     *
     * You are given an array prerequisites where prerequisites[i] = [ai, bi]
     * indicates that you must take course bi first if you want to take course ai.
     *
     *     For example, the pair [0, 1], indicates that
     *     to take course 0 you have to first take course 1.
     *
     * Return the ordering of courses you should take to finish all courses.
     * If there are many valid answers, return any of them.
     * If it is impossible to finish all courses, return an empty array.
     *
     *
     *
     * Example 1:
     *
     * Input: numCourses = 2, prerequisites = [[1,0]]
     * Output: [0,1]
     * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
     *
     * Example 2:
     *
     * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * Output: [0,2,1,3]
     * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
     * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
     *
     * Example 3:
     *
     * Input: numCourses = 1, prerequisites = []
     * Output: [0]
     *
     *
     *
     * Constraints:
     *
     *     1 <= numCourses <= 2000
     *     0 <= prerequisites.length <= numCourses * (numCourses - 1)
     *     prerequisites[i].length == 2
     *     0 <= ai, bi < numCourses
     *     ai != bi
     *     All the pairs [ai, bi] are distinct.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /*
          0. Let G(V,E) represent a directed, unweighted graph.
             V labeled from 0 to numCourses - 1

          1. Since it's mentioned that such an ordering may not always be possible,
          that means we have a cyclic graph.

          2. a pair such as [a, b] represents that the course b needs to be taken in order to do the course a
                - This can be represented as a directed edge b -> a in the graph.
             //Implied by below statement
             * You are given an array prerequisites where prerequisites[i] = [ai, bi]
             * indicates that you must take course bi first if you want to take course ai.
             *
             *     For example, the pair [0, 1], indicates that
             *     to take course 0 you have to first take course 1.
         */

        //for dfs
        Map<Integer, List<Integer>> adjList = Arrays.stream(prerequisites).collect(Collectors.groupingBy(pair->pair[1],
                Collectors.mapping(pair->pair[0], Collectors.toList())));

        //for detect cycles
        char[] color = new char[numCourses];
        Arrays.fill(color, 'W');
        List<Integer> result = new ArrayList<>(numCourses);
        boolean noCycle = true;
        for(int i=0;i<numCourses;i++){ //Time Complexity: O(V)
            if(!noCycle){
                break;
            }

            if(color[i]=='W') {
                noCycle = dfs(i, color, result, adjList);
            }
        }

        if(!noCycle){
            return new int[0];
        }

        Collections.reverse(result); ////Time Complexity: O(V)
        return result.stream().mapToInt(Integer::intValue).toArray(); //Total: Time Complexity: O(V+E)
    }

    public boolean dfs(int vertex, char[] color, List<Integer> result,Map<Integer, List<Integer>> adjList){
        color[vertex] = 'G';
        boolean noCycle = true;
        //if the map does not contain the key , then return empty list
        for(int neighbour : adjList.getOrDefault(vertex, Collections.emptyList())){ //Time Complexity: O(E)
            if(!noCycle){
                break;
            }
            if(color[neighbour]=='W'){
                noCycle = dfs(neighbour, color, result, adjList);
            } else if(color[neighbour]=='G'){ //back edge -> cycle
                return false;
            }
        }
        color[vertex] = 'B';
        result.add(vertex);
        return noCycle;
    }
}
