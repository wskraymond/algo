package com.mine.binarytree.bfs;

import com.mine.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    /**
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }

        int depth = 0;
        /**
         * use of add()/remove() becos it is unreasonable for full capacity and empty queue here.
         */
        Queue<TreeNode> q = new LinkedList<>(); //unbounded queue
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0;i<n;i++){
                TreeNode node = q.remove();
                if(node.left!=null) {
                    q.add(node.left);
                }

                if(node.right!=null) {
                    q.add(node.right);
                }
            }
            depth++;
        }

        return depth;
    }
}
