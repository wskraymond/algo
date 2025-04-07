package com.practice;

import java.util.*;
import java.util.stream.Collectors;

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
        /*
            Faster flatMapping of list of sorted linked lists
            1. moving with the current pointer
            2. return head pointer
         */
        if(lists.length==0){
            return null;
        }
        ListNode head = null, curr = null;
        Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, Comparator.comparingInt(node->node.val));
        minHeap.addAll(Arrays.stream(lists).filter(node->node!=null).collect(Collectors.toList())); //k*logk
        while(!minHeap.isEmpty()){
            ListNode node = minHeap.poll();
            ListNode clone = new ListNode(node.val);
            if(head==null){
                head = clone;
                curr = head;
            } else {
                curr.next = clone;
                curr = curr.next;
            }

            if(node.next!=null) {
                minHeap.offer(node.next);
            }
        }

        //n=total number of value in the merged list
        return head;//O(n*logk)
    }
}
