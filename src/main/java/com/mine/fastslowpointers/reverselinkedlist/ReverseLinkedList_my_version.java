package com.mine.fastslowpointers.reverselinkedlist;


public class ReverseLinkedList_my_version {
    /**
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,3,4,5]
     * Output: [5,4,3,2,1]
     * Example 2:
     *
     *
     * Input: head = [1,2]
     * Output: [2,1]
     * Example 3:
     *
     * Input: head = []
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is the range [0, 5000].
     * -5000 <= Node.val <= 5000
     */
    public ListNode reverseList(ListNode head) {
        ListNode p1=null;
        ListNode p2=head;
        while(p2.next!=null){
            ListNode temp = p2.next.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }

        return p2;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
