package com.mine.binarytree.dfs;

import com.mine.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SubtreeOfAnotherTree {
    /**
     * Given the roots of two binary trees root and subRoot,
     * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
     *
     * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
     * The tree could also be considered as a subtree of itself.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
     * Output: true
     * Example 2:
     *
     *
     * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in the root tree is in the range [1, 2000].
     * The number of nodes in the subRoot tree is in the range [1, 1000].
     * -104 <= root.val <= 104
     * -104 <= subRoot.val <= 104
     *
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot==null){ //edge case : subRoot is null matches null leaves or root=null
            return true;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode[]> stack2 = new ArrayDeque<>();
        TreeNode param = root;
        while(param!=null
                || !stack.isEmpty()){
            /* it won't do the case that current node = null , here,
                this edge case (null==null) will be covered on the top of method
             */
            if(param!=null){
                boolean result = isSameTree(param, subRoot, stack2);
                if(result){
                    return true;
                }

                stack.add(param);
                param = param.left;
            } else {
                param = stack.pop();
                param = param.right;
            }
        }

        return false; //one edge: root==null but subRoot!=null
    }

    private boolean isSameTree(TreeNode p, TreeNode q, Deque<TreeNode[]> stack) {
        stack.clear();
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

        return true; //one edge case: both root p and q is null
    }
}
