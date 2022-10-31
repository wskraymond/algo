package com.mine.binarytree.dfs;

import com.mine.binarytree.TreeNode;

import java.util.stream.IntStream;

/**
 * A path in a binary tree is a sequence of nodes
 * where each pair of adjacent nodes in the sequence has an edge connecting them.
 *
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
public class BTMaxPathSum {

    public int maxPathSum(TreeNode root) {
        int[] result = new int[1];
        result[0]=root.val;
        dfs(root, result);
        return result[0];
    }

    public int dfs(TreeNode node, int[] result){
        if(node==null){
            //won't set to max, default zero won't be counted as max
            return 0;
        }

        int val = node.val;
        int left = dfs(node.left, result);
        int right = dfs(node.right, result);
        result[0] = IntStream.of(result[0], left+val, right+val, val, left+right+val).max().getAsInt();
        return IntStream.of(left+val, right+val, val).max().getAsInt();
    }
}
