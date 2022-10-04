package com.mine.binarytree.binarysearchtree;

import com.mine.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBST {
    /**
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     *
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [2,1,3]
     * Output: true
     * Example 2:
     *
     *
     * Input: root = [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 104].
     * -231 <= Node.val <= 231 - 1
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        /*
            1. The left subtree of a node contains only nodes with keys less than the node's key. (<)
            2. The right subtree of a node contains only nodes with keys greater than the node's key. (>)
            3. This BST excludes (=)
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode param = root;

        /* Edge Case: only one node in a BST and value is MIN
            1. we cannot use this MIN value becos the input range includes it
            2. last value can be none and current value be MIN
            3. a flag to indicate ?
         */
        Integer lastVisited = null;
        while(param!=null
                || !stack.isEmpty()){
            if(param!=null){
                stack.push(param);
                param = param.left;
            } else {
                param = stack.pop();
                if(lastVisited!=null && param.val <= lastVisited.intValue()){  //trap!!!, definition of BST here does not include equality(=)
                    return false;
                }

                lastVisited = Integer.valueOf(param.val); //Integer.valueOf will cache value
                param = param.right;
            }
        }

        return true;
    }
}
