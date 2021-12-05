package com.mine.inplacereversal.reverselinkedlist2;

public class ReverseLinkedList2_my_version {
    /**
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     * Example 2:
     *
     * Input: head = [5], left = 1, right = 1
     * Output: [5]
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is n.
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode beforeLeft=null, afterRight = null;
        ListNode prev = null;
        ListNode curr = head;
        for(int i=1; i<=right;i++){
            if(i==left-1){
                beforeLeft = curr;
            } else if(i==right){
                afterRight = curr.next;
            }

            if(i>=left && i<=right){
                ListNode tempNext = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tempNext;
            } else {
                curr = curr.next;
            }
        }

        if(beforeLeft!=null && beforeLeft.next!=null) {
            beforeLeft.next.next = afterRight;
            beforeLeft.next = prev;
        } else {
            head.next = afterRight;
        }

        return beforeLeft!=null ? head : prev;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
