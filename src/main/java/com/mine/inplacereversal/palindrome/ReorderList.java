package com.mine.inplacereversal.palindrome;

import java.util.List;

public class ReorderList {
    /**
     * You are given the head of a singly linked-list. The list can be represented as:
     *
     * L0 → L1 → … → Ln - 1 → Ln
     * Reorder the list to be on the following form:
     *
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head==null || head.next==null){
            return;
        }

        ListNode endOfFirstHalf = endOfFirstHalf(head);
        ListNode headOfReversedSecondHalf = reverseList(endOfFirstHalf.next);
        endOfFirstHalf.next = null;
        ListNode p1 = head, p2 = headOfReversedSecondHalf;
        while(p2!=null){
            ListNode tmp1 = p1.next, tmp2 = p2.next;
            p1.next = p2;
            p2.next = tmp1;
            p1 = tmp1;
            p2 = tmp2;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev=null,curr=head;
        while(curr!=null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode s=head, f=head; //handle 0-> 1 , return s=0, no need to step forward
        while(f.next!=null&&f.next.next!=null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
}
