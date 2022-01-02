package com.mine.binarytree.dfs.preorder;

import com.mine.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal_stack_generic {
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
        TreeNode param = root;
        while(param!=null /*base case*/
                || !stack.isEmpty() /* return(restore the previous state)*/){
            if(param!=null){
                result.add(param.val);  //visit first
                param = param.left;     //first recursive call
            } else {
                param = stack.pop(); //backtrack to the parent
                param = param.right; //second recursive call: parent is done , left is done, gonna do its right
            }
        }
        return result;
    }
}
