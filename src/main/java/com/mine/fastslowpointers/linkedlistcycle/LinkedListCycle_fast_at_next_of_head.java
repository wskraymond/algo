package com.mine.fastslowpointers.linkedlistcycle;

public class LinkedListCycle_fast_at_next_of_head {
    /**
     * Given head, the head of a linked list,
     *      - determine if the linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list
     *      - that can be reached again by continuously following the next pointer.
     *
     *      - Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
     *          (Note that pos is not passed as a parameter)
     *
     * Return true if
     *  - there is a cycle in the linked list.
     *  - Otherwise, return false.
     *
     * Example 1:
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
     *
     * Example 2:
     *
     * Input: head = [1,2], pos = 0
     * Output: true
     * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
     *
     * Example 3:
     *
     * Input: head = [1], pos = -1
     * Output: false
     * Explanation: There is no cycle in the linked list.
     *
     *
     *
     * Constraints:
     *
     *     The number of the nodes in the list is in the range [0, 104].
     *     -105 <= Node.val <= 105
     *     pos is -1 or a valid index in the linked-list.
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        /**
         * Can you solve it using O(1) (i.e. constant) memory?
         */
        if(head==null){
            return false;
        }

        //odd case: 0 -> null
        //step 0: s->0, f-> null  //f==null , end of while loop

        //odd case: 0 -> 1 -> 2 -> null
        //step 0: s->0, f-> 1  //f!=null And f.next!=null
        //step 1: s->1, f-> 2  //f!=null but f.next==null , end of while loop

        //even case: 0 -> 1 -> 2 -> 3 -> null
        //step 0: s->0, f-> 1  //f!=null And f.next!=null
        //step 1: s->1, f-> 2  //f!=null And f.next!=null
        //step 1: s->2, f-> 3  //f!=null but f.next==null , end of while loop

        //to handle [1,2] with pos = 0 (head is the start/entrance of the cycle)
        //or [1] with pos = 0
        ListNode s = head, f = head.next; //head.next could be null in the case [0] with pos = 0
        while(f!=null
                && f.next!=null){
            if(s==f){
                return true;
            }

            s = s.next;
            f = f.next.next;
        }

        return false;
    }
}
