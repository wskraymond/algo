package com.mine.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BTConstructor {
    /**
     * Testing Purpose
     * used to convert Binary Tree Array to Linked Binary Tree
     * @param arr
     * @return
     */
    public TreeNode constructTree(Integer[] arr){
        if(arr==null || arr.length<1){
            return null;
        }

        int nodeIndex = 0;
        Queue<TreeNode> q = new LinkedList<>();

        TreeNode root = new TreeNode();
        root.val = arr[nodeIndex++];
        q.add(root);
        while (!q.isEmpty()){
            TreeNode parent = q.poll();
            if(parent!=null) {  //if it is null, parentIndex still has to do increment
                if (nodeIndex < arr.length) {
                    Integer val = arr[nodeIndex++];
                    TreeNode left = null;
                    if(val!=null) {
                        left = new TreeNode();
                        left.val = val;
                        parent.left = left;
                    }

                    q.add(left);
                }

                if (nodeIndex < arr.length) {
                    Integer val = arr[nodeIndex++];
                    TreeNode right = null;
                    if(val!=null) {
                        right = new TreeNode();
                        right.val = val;
                        parent.right = right;
                    }

                    q.add(right);
                }
            }
        }

        return root;
    }
}
