package com.mine.binarytree.binarysearchtree;

import com.mine.binarytree.TreeNode;

public class LowestCommonAncestor {
    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
     * <p>
     * According to the definition of LCA on Wikipedia: â€œThe lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * Output: 6
     * Explanation: The LCA of nodes 2 and 8 is 6.
     * Example 2:
     * <p>
     * <p>
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * Output: 2
     * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     * Example 3:
     * <p>
     * Input: root = [2,1], p = 2, q = 1
     * Output: 2
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [2, 105].
     * -109 <= Node.val <= 109
     * All Node.val are unique.
     * p != q
     * p and q will exist in the BST
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
            1. All Node.val are unique.
            2. p != q
            3. p and q will exist in the BST
         */

        /**
         * A node which split both p and q into different subtree , is the lowest common ancestor
         * becos you can never find a much lower node which contain both p and q in its subtree
         *
         * Thus, the search item in a BST is the split node:
         *      # assume balanced
         *      Time complexity: log(n)
         *      space complexity: log(n)
         */

        if (root == null) {
            return null;
        }

        TreeNode result = null;
        if (p.val > root.val && q.val > root.val) {
            result = lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            result = lowestCommonAncestor(root.left, p, q);
        } else { //both won't be in the same subtree
            result = root;
        }

        return result;
    }
}
