package com.mine.greedy.minheap.handofstraights;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HandOfStraights_sorted_minheap {
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
        final int k = n/groupSize;
        int[] sortedHand = Arrays.copyOf(hand, n); //space: O(n)
        Arrays.sort(sortedHand); //Time: O(nlogn) , space: quicksort=O(logn) or mergesort=O(n)

        //Group: {Largest Number, size}
        //minHeap sorted by value
        //sample 1: [1,2,3,6,2,3,4,7,8], groupSize=3
        //sample 2: [1,2,3,1,2,3,4,5,6] , groupSize=3
        Queue<int[]> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(g->g[0])); //space: O(k)
        for(int i=0;i<n;i++){ //O(n)
            if(minHeap.size() > k){
                return false;
            }

            int[] g;
            if(minHeap.isEmpty() || minHeap.peek()[0]==sortedHand[i]){
                g = new int[]{sortedHand[i], 1};
            } else if(minHeap.peek()[0]+1==sortedHand[i]){
                g = minHeap.poll(); //time: O(logk)
                g[0]++;
                g[1]++;
            } else {
                return false;
            }

            if(g[1]<groupSize){
                minHeap.offer(g); //time: O(logk)
            }
        }

        //edge case with groupSize=2:  1 , 2, 1, 2, 1, 2 , 7
        //to deal with above edge case , we have to check if heap is empty.
        return minHeap.isEmpty();   //Total time: nlogn + nlogk=O(nlogn) , Total Space: n + n + k = log(n)
    }
}
