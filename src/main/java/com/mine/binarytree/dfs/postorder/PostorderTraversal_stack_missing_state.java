package com.mine.binarytree.dfs.postorder;

import com.mine.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal_stack_missing_state {
    /**
     * Given the root of a binary tree,
     * return the postorder traversal of its nodes' values.
     *
     * Example 1:
     *
     * Input: root = [1,null,2,3]
     * Output: [3,2,1]
     *
     * Example 2:
     *
     * Input: root = []
     * Output: []
     *
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     *
     *
     *
     * Constraints:
     *
     *     The number of the nodes in the tree is in the range [0, 100].
     *     -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode param = root;
        /**
         * The challenge here is
         *     1. whenever we backtrack to the parent,
         *     we don't know which side we still haven't finished
         *
         *     2. two Solutions
         *          i) we have to mark(save) which side has been visited instead of only store the node
         *          ii) reverse of postorder
         *              (Order: left, right, visit(addLast) => visit(addFirst, right, left))
         */
        while(param!=null || !stack.isEmpty()){
            while(param!=null) {
                stack.push(param);
                TreeNode left = param.left;
                while (left != null) {
                    stack.push(left);
                    left = left.left;
                }

                param = param.right;
            }
            result.add(stack.pop().val);
        }

        return result;
    }
}