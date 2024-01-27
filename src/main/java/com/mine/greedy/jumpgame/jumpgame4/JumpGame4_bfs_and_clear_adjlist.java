package com.mine.greedy.jumpgame.jumpgame4;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JumpGame4_bfs_and_clear_adjlist {

    /**
     * Given an array of integers arr,
     *
     * you are initially positioned at the first index of the array.
     *
     * In one step you can jump from index i to index:
     *
     * i + 1 where: i + 1 < arr.length.
     * i - 1 where: i - 1 >= 0.
     * j where: arr[i] == arr[j] and i != j.
     * Return the minimum number of steps to reach the last index of the array.
     *
     * Notice that you can not jump outside of the array at any time.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
     * Output: 3
     * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
     * Example 2:
     *
     * Input: arr = [7]
     * Output: 0
     * Explanation: Start index is the last index. You do not need to jump.
     * Example 3:
     *
     * Input: arr = [7,6,9,6,9,6,9,7]
     * Output: 1
     * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 5 * 104
     * -108 <= arr[i] <= 108
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> numToIndexMap = IntStream.range(0, arr.length)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.groupingBy(
                        i->Integer.valueOf(arr[i]))
                );

        int level = 0;
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0]=true;
        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0;i<n;i++){
                int currIndex = q.poll();
                if(currIndex==arr.length-1){
                    return level;
                }
                Stream.concat(Stream.of(currIndex-1, currIndex+1), numToIndexMap.get(arr[currIndex]).stream())
                        .mapToInt(Integer::intValue)
                        .distinct()
                        .filter(j->j>=0&&j<arr.length)
                        .forEach(neighbor->{
                            if(!visited[neighbor]){
                                visited[neighbor]=true;
                                q.offer(neighbor);
                            }
                        });

                //this trick helps reduce redundant checking which takes E=O(V^2) in worst cases.
                numToIndexMap.get(arr[currIndex]).clear();
            }

            level++; //be careful: jumps/steps does not include the first index , becos it doesn't jump (already on it)
        }

        return -1;
    }
}
