package com.mine.binarytree;

import java.util.*;
import java.util.stream.Collectors;

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
public class SerializeAndDeserializeBT_DFS {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root,result);
        return String.join(",", result.stream()
                .map(String::valueOf)   //String.valueOf handles null case.
                .toArray(String[]::new));
    }

    private void dfs(TreeNode node, List<Integer> result){
        if(node==null){
            result.add(null);
            return;
        }

        result.add(node.val);
        dfs(node.left,result);
        dfs(node.right, result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        Integer[] arr = Arrays.stream(split).map(s->!s.equalsIgnoreCase("null")?Integer.valueOf(s):null).toArray(Integer[]::new);
        return dfs(arr, new int[1]);
    }

    public TreeNode dfs(Integer[] arr, int[] index){
        if(index[0]>=arr.length){
            throw new IllegalArgumentException();
        }

        Integer val = arr[index[0]++];
        if(val==null){
            return null;
        }

        TreeNode node = new TreeNode(val);
        node.left = dfs(arr, index);
        node.right = dfs(arr, index);
        return node;
    }
}
