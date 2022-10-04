package com.mine.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

public class SameTree {
    /**
     * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: p = [1,2,3], q = [1,2,3]
     * Output: true
     * Example 2:
     *
     *
     * Input: p = [1,2], q = [1,null,2]
     * Output: false
     * Example 3:
     *
     *
     * Input: p = [1,2,1], q = [1,1,2]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in both trees is in the range [0, 100].
     * -104 <= Node.val <= 104
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        TreeNode paramP = p;
        TreeNode paramQ = q;
        while(paramP!=null
                || paramQ!=null
                || !stack.isEmpty()){
            if(paramP==null^paramQ==null){
                return false;
            }

            if(paramP!=null){
                if(paramP.val!=paramQ.val){
                    return false;
                }

                stack.push(new TreeNode[]{paramP, paramQ});
                paramP = paramP.left;
                paramQ = paramQ.left;
            } else {
                TreeNode[] arr = stack.pop();
                paramP = arr[0].right;
                paramQ = arr[1].right;
            }
        }

        return true;
    }
}
