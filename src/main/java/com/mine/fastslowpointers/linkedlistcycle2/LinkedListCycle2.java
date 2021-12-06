package com.mine.fastslowpointers.linkedlistcycle2;

public class LinkedListCycle2 {
    /**
     * Given the head of a linked list,
     *      return the node where the cycle begins.
     *      If there is no cycle, return null.
     *
     * There is a cycle in a linked list
     *      if there is some node in the list that can be reached again by continuously
     *      following the next pointer. Internally, pos is used to denote the index of
     *      the node that tail's next pointer is connected to (0-indexed).
     *      It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     *
     * Do not modify the linked list.
     *
     * Example 1:
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     *
     * Example 2:
     *
     * Input: head = [1,2], pos = 0
     * Output: tail connects to node index 0
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     *
     * Example 3:
     *
     * Input: head = [1], pos = -1
     * Output: no cycle
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
    public ListNode detectCycle(ListNode head) {
        /**
         * edge case:
         *  1. null
         *  2. 0 -> null
         *  3. 0 -> 1 -> 0  //pos = 0
         *
         *  non-trivial cases: ( different combinations )
         *  1. -3 -> -2 -> -1 -> 0 -> 1 -> 2 -> 3 -> 4 -> 0 //pos = 0, cycle length = 5, F = 3 , h = 3%5 = 3, a = 5 - h = 2
         *  2. -4 -> -3 -> -2 -> -1 -> 0 -> 1 -> 2 -> 0     //pos = 0, cycle length = 3, F = 4 , h = 4%3 = 1, a = 3 - h = 2
         *  3. -2 -> -1 -> 0 -> 1 -> 0 //pos = 0, cycle length = 2, F = 2, h = 2%2 = 0 , a = 2 - h = move 2 step from 0 (finally at node 0)
         *  4. -2 -> -1 -> 0 -> 0 //pos = 0, cycle length = 1, F=2 , h=2%1 = 0, a = 1 - 0 = move 1 step from node 0 (finally at node 0)
         */
        if(head==null){
            return null;
        }

        //detect a cycle
        ListNode s = head, f = head;
        ListNode p1 = head, p2 = null;
        while(f.next!=null
                && f.next.next!=null){
           s = s.next;
           f = f.next.next;
           if(s==f){
               p2 = f;
               break;
           }
        }

        if(p2!=null){ //if there is a cycle and both s & f intersects at a
            while (p1!=p2){
                p1 = p1.next;
                p2 = p2.next;
            }
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
