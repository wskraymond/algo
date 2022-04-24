package com.mine.binarytree.bfs;

import com.mine.binarytree.TreeNode;

import java.util.*;

public class BTLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return Collections.emptyList();
        }

        List<List<Integer>> result =  new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int n = q.size();
            for(int i=0;i<n;i++){
                TreeNode node = q.poll();
                level.add(node.val);
                if(node.left!=null){
                    q.offer(node.left);
                }

                if(node.right!=null){
                    q.offer(node.right);
                }
            }
            result.add(level);
        }

        return result;
    }
}
