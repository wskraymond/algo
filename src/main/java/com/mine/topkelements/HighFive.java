package com.mine.topkelements;

import java.util.*;

public class HighFive {
    /**
     * Given a list of the scores of different students,
     *      - items, where items[i] = [IDi, scorei] represents one score from a student with IDi, calculate each student's top five average.
     *
     * Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. Sort result by IDj in increasing order.
     *
     * A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.
     *
     *
     *
     * Example 1:
     *
     * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
     * Output: [[1,87],[2,88]]
     * Explanation:
     * The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
     * The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
     * Example 2:
     *
     * Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
     * Output: [[1,100],[7,100]]
     *
     *
     * Constraints:
     *
     * 1 <= items.length <= 1000
     * items[i].length == 2
     * 1 <= IDi <= 1000
     * 0 <= scorei <= 100
     * For each IDi, there will be at least five scores.
     *
     * @param items
     * @return
     */
    public int[][] highFive(int[][] items) {
        Map<Integer, Queue<Integer>> avgMap = new TreeMap<>();
        for(int[] item: items){     //O(n)
            final int id = item[0];
            final int score = item[1];
            if(!avgMap.containsKey(id)){                //worst case: O(logn) for n unique ids
                avgMap.put(id, new PriorityQueue<>());  //worst case: O(logn) for n unique ids
            }

            Queue<Integer> minHeap = avgMap.get(id);
            minHeap.offer(score);   //O(log(1)) for size<=5 heap
            if(minHeap.size() > 5){
                minHeap.poll();     //O(log(1)) for size<=5 heap
            }
        }

        //Iteration: O(n)
        //top 5 avg: O(5*log5) = O(1)
        return avgMap.entrySet().stream()
                .map(e->new int[]{e.getKey(), (int) e.getValue().stream().mapToInt(Integer::intValue).average().getAsDouble()})
                .toArray(int[][]::new);

        //Total: O(nlogn)
    }
}
