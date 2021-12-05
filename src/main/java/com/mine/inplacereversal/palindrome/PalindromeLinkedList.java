package com.mine.inplacereversal.palindrome;

public class PalindromeLinkedList {
    /**
     * Given the head of a singly linked list,
     * return true if it is a palindrome.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [1,2,2,1]
     * Output: true
     *
     * Example 2:
     *
     * Input: head = [1,2]
     * Output: false
     *
     *
     *
     * Constraints:
     *
     *     The number of nodes in the list is in the range [1, 105].
     *     0 <= Node.val <= 9
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        /**
         * Could you do it in O(n) time and O(1) space?
         */

        //edge case
        if(head==null){
            return true;
        }

        ListNode endOfFirstHalf = endOfFirstHalf(head);
        ListNode headOfReversedSecondHalf = reverseList(endOfFirstHalf.next);

        ListNode p1=head , p2= headOfReversedSecondHalf;
        boolean result = true;
        while (p2!=null && result){
            result &= p1.val==p2.val;
            p1 = p1.next;
            p2 = p2.next;
        }


        //Actually, endOfFirstHalf.next is never updated after reverseList method is called
        //Thus, we don't need to reset the next of end of the first half
        reverseList(headOfReversedSecondHalf);

        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }

        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode s=head, f=head;
        while(f.next!=null && f.next.next!=null){
            s=s.next; //speed=1 step per loop
            f=f.next.next;  //speed 2 steps per loop
        }

        return s;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
