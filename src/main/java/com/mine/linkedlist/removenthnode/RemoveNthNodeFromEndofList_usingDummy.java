package com.mine.linkedlist.removenthnode;

public class RemoveNthNodeFromEndofList_usingDummy {
    /**
     * Given the head of a linked list,
     * remove the nth node from the end of the list
     *
     * and return its head.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     *
     * Example 2:
     *
     * Input: head = [1], n = 1
     * Output: []
     *
     * Example 3:
     *
     * Input: head = [1,2], n = 1
     * Output: [1]
     *
     *
     *
     * Constraints:
     *
     *     The number of nodes in the list is sz.
     *     1 <= sz <= 30
     *     0 <= Node.val <= 100
     *     1 <= n <= sz
     *
     *
     *
     * Follow up: Could you do this in one pass?
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /*
        use dummy -> head to avoid null scenario
        , which can significantly simplify the code below
         */

        /*
        edge case 1:  0 -> null , n=1  => return null
        edge case 2:  0 -> 1 -> null , n=2 => return 1
        edge case 3: 0 -> 1 -> 2 -> null, n=3 => return 1
         */
        ListNode forwardN = head, nodeBeforeN=null, toBeRemoval=head;
        for(int i=0;i<n;i++){ //O(forward steps)
            if(forwardN!=null){
                forwardN = forwardN.next;
            } else {
                throw new IllegalArgumentException("n is larger than the length of list");
            }
        }

        while(forwardN!=null){ //O(remaining Steps)
            nodeBeforeN = toBeRemoval;
            toBeRemoval = toBeRemoval.next;
            forwardN = forwardN.next;
        }

        ListNode result = null;
        if(nodeBeforeN!=null){
            nodeBeforeN.next = toBeRemoval.next;
            toBeRemoval.next = null;
            result = head;
        } else {
            result = toBeRemoval.next;
            toBeRemoval.next = null;
        }

        return result; //O(n)
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
