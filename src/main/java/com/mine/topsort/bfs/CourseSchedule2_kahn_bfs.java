package com.mine.topsort.bfs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CourseSchedule2_kahn_bfs {
    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     *
     *     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     *
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
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
        Map<Integer, List<Integer>> adjList = Arrays.stream(prerequisites).collect(Collectors.groupingBy(
          pair->pair[1],
          Collectors.mapping(pair->pair[0], Collectors.toList())
        ));

        int[] inDegree = new int[numCourses];
        Arrays.stream(prerequisites).collect(Collectors.groupingBy(pair->pair[0],
                Collectors.mapping(pair->pair[1], Collectors.reducing(0, v->1, Integer::sum))))
                .forEach((k,count)->inDegree[k]=count);

        Queue<Integer> zeroDegree = new LinkedList<Integer>();
        IntStream.range(0, inDegree.length).filter(i->inDegree[i]==0).forEach(i->zeroDegree.offer(i));

        int i=0;
        int[] result = new int[numCourses];
        while(!zeroDegree.isEmpty()){
            int vertex = zeroDegree.poll();
            result[i++] = vertex; //if this is last one, then i==numCourses

            for(int neighbour : adjList.getOrDefault(vertex, Collections.emptyList())){
                inDegree[neighbour]--;

                if(inDegree[neighbour]==0){
                    zeroDegree.offer(neighbour);
                }
            }
        }

        if(i<numCourses){
            return new int[0];
        }

        return result;
    }
}
