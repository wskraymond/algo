package com.mine.kwaymerge.mergelist;

import java.util.List;

public class MergeTwoSortedLists {
    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     *
     * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
     *
     * Return the head of the merged linked list.
     * Example 2:
     *
     * Input: list1 = [], list2 = []
     * Output: []
     *
     * Example 3:
     *
     * Input: list1 = [], list2 = [0]
     * Output: [0]
     *
     *
     *
     * Constraints:
     *
     *     The number of nodes in both lists is in the range [0, 50].
     *     -100 <= Node.val <= 100
     *     Both list1 and list2 are sorted in non-decreasing order.
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null){
            return list2;
        }

        if(list2==null){
            return list1;
        }

        ListNode head = null;
        ListNode merged = null;
        while(list1!=null&&list2!=null){
            if(list1.val> list2.val){
                if(merged!=null){
                    merged.next = list2;
                    merged = merged.next;
                } else {
                    merged = list2;
                }
                list2 = list2.next;
            } else {
                if(merged!=null){
                    merged.next = list1;
                    merged = merged.next;
                } else {
                    merged = list1;
                }
                list1 = list1.next;
            }

            if(head==null){
                head = merged;
            }
        }

        if(list1!=null){
            merged.next = list1;
        }

        if(list2!=null){
            merged.next = list2;
        }

        return head;
    }

    
//      Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
