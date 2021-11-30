package com.mine.fastslowpointers.reverselinkedlist;

public class ReverseLinkedList_recursive {
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
        /**
         * recurrence relation:
         *      f(k):= write(nk.next.next = nk)
         *              And write(nk.next = null)
         *              And f(k+1) //top-down reverse it
         *
         * Base case:
         *      f(tail->null) = return(tail)  //tail is the head of the reversed linkedlist
         */
        //if it is empty list or tail
        if(head==null || head.next==null){
            return head;
        }

        //goes to the tail of the list
        ListNode tail = reverseList(head.next);

        //then backtrack to reverse list
        head.next.next = head;  //set next node's pointer to itself
        head.next = null;       //unset its next reference (unbind to the forward node)

        //return tail as the head of reversed list
        return tail;
    }
}
