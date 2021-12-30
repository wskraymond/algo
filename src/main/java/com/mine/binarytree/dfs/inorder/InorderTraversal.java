package com.mine.binarytree.dfs.inorder;

import com.mine.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {
    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     *
     * Example 1:
     *
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
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
     * Constraints:
     *
     *     The number of nodes in the tree is in the range [0, 100].
     *     -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        dfs(root, result);

        return result;
    }

    private void dfs(TreeNode parentNode, List<Integer> result){
        if(parentNode==null){
            return;
        }

        dfs(parentNode.left, result);
        result.add(parentNode.val); //in between its left and its right recursive calls
        dfs(parentNode.right, result);
    }
}

