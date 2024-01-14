package com.mine.greedy.minheap.handofstraights;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HandOfStraights_for_each_unique_heap_sort_and_map_for_consecutive_checck {
    /**
     * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize,
     * and consists of groupSize consecutive cards.
     *
     * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
     *
     * return true if she can rearrange the cards, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
     * Output: true
     * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
     * Example 2:
     *
     * Input: hand = [1,2,3,4,5], groupSize = 4
     * Output: false
     * Explanation: Alice's hand can not be rearranged into groups of 4.
     *
     *
     *
     * Constraints:
     *
     * 1 <= hand.length <= 104
     * 0 <= hand[i] <= 109
     * 1 <= groupSize <= hand.length
     *
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length%groupSize!=0){
            return false;
        }
        final int n = hand.length;
        Map<Integer,Long> countMap = Arrays.stream(hand).mapToObj(Integer::valueOf) //Time: O(n)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Queue<Integer> minHeap = new PriorityQueue<>(countMap.keySet()); //heapify: Time=O(n)
        while (!minHeap.isEmpty()){ //O(#unique)
            int num = minHeap.peek();
            for(int i=num;i<num+groupSize;i++){
                Long newCount = countMap.computeIfPresent(i, (key,val)->val-1); //Time=O(1)
                if(newCount==null){ //edge case with groupSize=2:  1 , 2, 1, 2, 1, 2 , 7
                    return false;
                }else if(newCount==0){
                    if(minHeap.peek()!=i){
                        return false;
                    }
                    minHeap.poll(); //Time=O(logn)
                }
            }
        } //O(sum{uniquexcount})=O(n)

        return true;
    }
}
