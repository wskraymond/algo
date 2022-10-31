package com.mine.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeBT_BFS {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        result.append(root!=null?root.val:"null");
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node!=null){
                result.append(",").append(node.left!=null ? node.left.val : "null")
                        .append(",").append(node.right!=null ? node.right.val : "null");
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        Integer[] arr = Arrays.stream(split).map(s->!s.equalsIgnoreCase("null")?Integer.valueOf(s):null).toArray(Integer[]::new);
        return constructTree(arr);
    }

    /**
     * Testing Purpose
     * used to convert Binary Tree Array to Linked Binary Tree
     * @param arr
     * @return
     */
    public TreeNode constructTree(Integer[] arr){
        if(arr==null || arr.length<1 || arr[0]==null){
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
