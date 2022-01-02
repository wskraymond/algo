package com.mine.binarytree.dfs.preorder;

import com.mine.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {
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
        classicDfs(root, result);
        return result;
    }

    private void classicDfs(TreeNode parent, List<Integer> result){
        if(parent==null){
            return;
        }

        result.add(parent.val);
        classicDfs(parent.left, result);
        classicDfs(parent.right, result);
    }
}
