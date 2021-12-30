package com.mine.binarytree.dfs.inorder;

import com.mine.binarytree.TreeNode;

import java.util.*;

public class InorderTraversal_stack {
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
        /**
         * stack(LIFO): push() & pop() & peek()
         * queue(FIFO): offer() & poll() & peek()
         * (throw exception) : add() & remove() & element()
         *
         * dfs(Node param) = {dfs(param.left),
         *                      visit(param),
         *                    dfs(param.right)}
         */

        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode param = root;
        while (param!=null || !stack.isEmpty()){ //if param==null or end of return....
            while(param!=null){     //if param==null , then return
                stack.push(param);  //enter function: save param
                param = param.left; //input param: dfs(param.left)
            }
            param = stack.pop();    //return function: restore param
            result.add(param.val);  //visit(param)
            param = param.right;    //input param: dfs(param.right)
        }
        return result;
    }
}

