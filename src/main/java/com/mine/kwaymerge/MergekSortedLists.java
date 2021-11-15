package com.mine.kwaymerge;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergekSortedLists {
    /**
     * You are given an array of k linked-lists lists,
     * each linked-list is sorted in ascending order.
     *
     * Merge all the linked-lists
     * into one sorted linked-list and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     * Explanation: The linked-lists are:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * merging them into one sorted list:
     * 1->1->2->3->4->4->5->6
     * Example 2:
     *
     * Input: lists = []
     * Output: []
     * Example 3:
     *
     * Input: lists = [[]]
     * Output: []
     *
     *
     * Constraints:
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] is sorted in ascending order.
     * The sum of lists[i].length won't exceed 10^4.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length<=0){
            return null;
        }

        ListNode head = null;
        ListNode next = null;
        Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, Comparator.comparingInt(node->node.val));
        for(ListNode node:lists){ //O(k)
            if(node!=null) {
                minHeap.add(node); //O(logk)
            }
        }

        while(!minHeap.isEmpty()){ //O(n)
            ListNode min = minHeap.poll(); //O(logk)
            if(next==null){
                next = new ListNode(min.val);
                head = next;
            } else {
                next.next = new ListNode(min.val);
                next = next.next;
            }

            if(min.next!=null){
                minHeap.add(min.next); //O(logk)
            }
        }

        return head;  //total: O(nlogk)
    }
}
