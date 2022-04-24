package com.mine.binarytree.bfs;

import com.mine.binarytree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class ZigzagLevelOrderTraversal {
    /**
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
     *
     *
     *
     * Example 1:
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
     *
     * Example 2:
     *
     * Input: root = [1]
     * Output: [[1]]
     *
     * Example 3:
     *
     * Input: root = []
     * Output: []
     *
     *
     *
     * Constraints:
     *
     *     The number of nodes in the tree is in the range [0, 2000].
     *     -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /*
        One of the keys here is to store the values
        that are of the same level with the deque (double-ended queue) data structure,
         where we could add new values on either end of a queue.
            1. Both LinkedList and ArrayDeque is Deque
            2. ArrayList is not a deque
         */

        if(root==null){
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(!q.isEmpty()){
            Deque<Integer> bucket = new ArrayDeque<>();
            int n = q.size();
            for(int i=0;i<n;i++){
                TreeNode node = q.poll();
                if(level%2==0){
                    bucket.add(node.val);
                } else {
                    bucket.push(node.val);
                }

                if(node.left!=null){
                    q.offer(node.left);
                }

                if(node.right!=null){
                    q.offer(node.right);
                }
            }

            result.add(bucket.stream().collect(Collectors.toList()));
            level++;
        }

        return result;
    }
}
