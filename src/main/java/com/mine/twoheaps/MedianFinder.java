package com.mine.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
    private final Queue<Integer> lowerHalf;
    private final Queue<Integer> higherHalf;

    public MedianFinder() {
        lowerHalf = new PriorityQueue<>(Comparator.reverseOrder()); //max heap
        higherHalf = new PriorityQueue<>(); //min heap
    }

    public void addNum(int num) {
        lowerHalf.offer(num);
        int max = lowerHalf.poll();
        higherHalf.offer(max);

        if(lowerHalf.size() < higherHalf.size()){
            int min = higherHalf.poll();
            lowerHalf.offer(min);
        }
    }

    public double findMedian() {
        if(lowerHalf.size() > higherHalf.size()){
            return lowerHalf.peek();
        }

        return (lowerHalf.peek() + higherHalf.peek())/2.0D;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
