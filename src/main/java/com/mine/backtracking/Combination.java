package com.mine.backtracking;

import java.util.*;

public class Combination {
    /**
     * Given two integers n and k,
     * return all possible combinations of k numbers
     * out of the range [1, n].
     *
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * Example 2:
     *
     * Input: n = 1, k = 1
     * Output: [[1]]
     *
     *
     *
     * Constraints:
     *
     *     1 <= n <= 20
     *     1 <= k <= n
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        /**
         * deque is short for "double ended queue"
         *  - Deques can also be used as LIFO (Last-In-First-Out) stacks.
         *  - When a deque is used as a queue, FIFO (First-In-First-Out) behavior results.
         *
         */
        List<List<Integer>> result = new LinkedList<>();
        backtrack(n,k,0,0,  new LinkedList<>(), result);

        /**
         * Time Complexity = O(k * nCr)
         */
        return result;
    }

    public void backtrack(int n, int k, int r, int first, Deque<Integer> c, List<List<Integer>> result){
        if(r==k){ // or c.size() == k  // then we don't need param - r
            result.add(new LinkedList<>(c)); //O(k)
        }

        for(int i=first; i<n; i++){
            c.add(i+1);
            backtrack(n,k, r+1, i+1, c, result); //O(nCr)
//            c.remove(c.size()-1);
            c.removeLast();
        }
    }
}
