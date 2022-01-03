package com.mine.binarytree.dfs.postorder;

import com.mine.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal {
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
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode parent, List<Integer> result){
        if(parent==null){
            return;
        }

        dfs(parent.left, result);
        dfs(parent.right, result);
        result.add(parent.val);
    }
}
