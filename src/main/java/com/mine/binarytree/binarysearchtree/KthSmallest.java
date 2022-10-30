package com.mine.binarytree.binarysearchtree;

import com.mine.binarytree.TreeNode;

public class KthSmallest {
    /**
     * Given the root of a binary search tree, and an integer k,
     * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,1,4,null,2], k = 1
     * Output: 1
     * Example 2:
     *
     *
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     * Output: 3
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 1 <= k <= n <= 104
     * 0 <= Node.val <= 104
     *
     *
     * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        /*
        Follow up: If the BST is modified often
        (i.e., we can do insert and delete operations)
        and you need to find the kth smallest frequently, how would you optimize?

        #Let's use here the same logic as for LRU cache design,
        and combine an indexing structure (we could keep BST here) with a double linked list.

        Such a structure would provide:

        O(H) time for the insert and delete.

        O(k) for the search of kth smallest.

        #The overall time complexity for insert/delete + search of kth smallest is O(H + k) instead of O(2H+k).

        Complexity Analysis

        Time complexity for insert/delete + search of kth smallest: O(H + k), where H is a tree height.
        O(logN+k) in the average case, O(N + k)O(N+k) in the worst case.

        Space complexity : O(N) to keep the linked list.
         */

        /**
         * Time Complexity:
         *  1. Balanced Tree: logN + k
         *      Why ?
         *          a) it has to traverse from root to the leaf => logN
         *          b) and then pop to visit k elements => k
         *  2. (worst case) Unbalanced Tree: N + k
         *
         *  Space Complexity: logN + 1 = O(log N)
         */

        int[] result = new int[1];
        inOrder(root, 1, k, result);
        return result[0];
    }

    public int inOrder(TreeNode node, int i, int k, int[] result){
        if(node==null){
            return i;
        }

        i = inOrder(node.left, i, k, result);
        if(i++==k){ //visit
            result[0] = node.val;
            return i;
        }

        i = inOrder(node.right, i , k, result);
        return i;
    }
}
