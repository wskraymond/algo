package com.mine.fastslowpointers.linkedlistcycle2;

import com.mine.greedy.BoatsToSavePpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testlinkedlistcycle2 {
    private static LinkedListCycle2 sol = new LinkedListCycle2();

    private ListNode construct(int[] nums, int pos){
        if(nums==null || nums.length<=0
                || pos>=nums.length){
            throw new IllegalArgumentException();
        }

        ListNode head = new ListNode();
        head.val = nums[0];

        ListNode curr = head, entrance = null;
        if(pos==0){
            entrance = head;
        }
        for(int i=1;i<nums.length;i++){
            curr.next = new ListNode();
            curr.next.val = nums[i];
            curr = curr.next;

            if(i==pos){
                entrance = curr;
            }
        }

        //link the tail to the entrance of the cycle
        curr.next = entrance;

        return head;
    }

    private ListNode getEntranceByExplicitPos(ListNode head, int pos){
        ListNode curr=head, entrance = null;
        if(pos==0){
            entrance = curr;
        }
        for(int i=1;i<=pos;i++){
            curr = curr.next;
            if(i==pos){
                entrance = curr;
            }
        }

        return entrance;
    }
    /**
     * edge case:
     *  1. null
     *  2. 0 -> null
     *  3. 0 - 1 -> null
     *  4. 0 -> 1 -> 0  //pos = 0
     *  5. 0 -> 0       //pos = 0
     *
     *  Cycle cases: ( different combinations )
     *  1. -3 -> -2 -> -1 -> 0 -> 1 -> 2 -> 3 -> 4 -> 0 //pos = 0, cycle length = 5, F = 3 , h = 3%5 = 3, a = 5 - h = 2
     *  2. -4 -> -3 -> -2 -> -1 -> 0 -> 1 -> 2 -> 0     //pos = 0, cycle length = 3, F = 4 , h = 4%3 = 1, a = 3 - h = 2
     *  3. -2 -> -1 -> 0 -> 1 -> 0 //pos = 0, cycle length = 2, F = 2, h = 2%2 = 0 , a = 2 - h = move 2 step from 0 (finally at node 0)
     *  4. -2 -> -1 -> 0 -> 0 //pos = 0, cycle length = 1, F=2 , h=2%1 = 0, a = 1 - 0 = move 1 step from node 0 (finally at node 0)
     */

    @Test
    public void testEmpty(){
        assertEquals(null, sol.detectCycle(null));
    }

    @Test
    public void testOneNodeWithoutCycle(){
        ListNode input = construct(new int[]{0}, -1);
        ListNode expected = getEntranceByExplicitPos(input, -1);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testTwoNodeWithoutCycle(){
        ListNode input = construct(new int[]{0, 1}, -1);
        ListNode expected = getEntranceByExplicitPos(input, -1);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testOneNodeWithCycle(){
        ListNode input = construct(new int[]{0}, 0);
        ListNode expected = getEntranceByExplicitPos(input, 0);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testTwoNodeWithCycle(){
        ListNode input = construct(new int[]{0, 1}, 0);
        ListNode expected = getEntranceByExplicitPos(input, 0);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testCase1(){
        ListNode input = construct(new int[]{-3, -2, -1, 0, 1 , 2, 3, 4}, 3);
        ListNode expected = getEntranceByExplicitPos(input, 3);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testCase2(){
        ListNode input = construct(new int[]{-4, -3, -2, -1, 0, 1 , 2}, 4);
        ListNode expected = getEntranceByExplicitPos(input, 4);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testCase3(){
        ListNode input = construct(new int[]{-2, -1, 0, 1 }, 2);
        ListNode expected = getEntranceByExplicitPos(input, 2);
        assertEquals(expected, sol.detectCycle(input));
    }

    @Test
    public void testCase4(){
        ListNode input = construct(new int[]{-2, -1, 0}, 2);
        ListNode expected = getEntranceByExplicitPos(input, 2);
        assertEquals(expected, sol.detectCycle(input));
    }
}
