package com.mine.binarytree.dfs.preorder;

import com.mine.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal_stack {
    /**
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,null,2,3]
     * Output: [1,2,3]
     * Example 2:
     *
     * Input: root = []
     * Output: []
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode parent = stack.pop();
            if(parent!=null) {
                result.add(parent.val);
                if(parent.right!=null) {
                    stack.push(parent.right);
                }

                if(parent.left!=null) {
                    stack.push(parent.left);
                }
            }
        }

        return result;
    }
}
