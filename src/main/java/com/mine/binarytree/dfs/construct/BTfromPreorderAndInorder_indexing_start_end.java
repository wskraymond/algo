package com.mine.binarytree.dfs.construct;

import com.mine.binarytree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class BTfromPreorderAndInorder_indexing_start_end {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0 || inorder.length==0){
            return null;
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        IntStream.range(0, inorder.length).forEach(i->indexMap.put(inorder[i],i));

        TreeNode root = new TreeNode();
        linkLeftAndRight(root,indexMap,
                preorder, 0, preorder.length,
                inorder, 0, inorder.length);
        return root;
    }

    private void linkLeftAndRight(TreeNode node,
                                  Map<Integer, Integer> indexMap,
                                  int[] preorder,
                                  int pStart,
                                  int pEnd,
                                  int[] inorder,
                                  int iStart,
                                  int iEnd){
        //base case
        if(pStart<0 || pStart>=pEnd || pEnd>preorder.length
            || iStart<0 || iStart>=iEnd || iEnd>inorder.length){
            throw new IllegalArgumentException();
        }

        node.val = preorder[pStart];
//        int index = Arrays.binarySearch(inorder, iStart, iEnd, node.val); //only for binary tree's inorder traversal
//        int index = IntStream.range(iStart,iEnd).filter(i->inorder[i]==node.val).findFirst().orElse(-1);
        int index = indexMap.getOrDefault(node.val, -1);
        if(index<0){
            throw new IllegalArgumentException();
        }

        int iLeftS =iStart, iLeftE = index;
        int leftSize = iLeftE - iLeftS;
        if(leftSize > 0){
            int pLeftS = pStart + 1;
            int pLeftE = pLeftS + leftSize;
            node.left = new TreeNode();
            linkLeftAndRight(node.left,indexMap,
                    preorder, pLeftS,pLeftE,
                    inorder, iLeftS, iLeftE);
        }

        int iRightS = index + 1, iRightE = iEnd;
        int rightSize = iRightE - iRightS;
        if(rightSize > 0){
            int pRightS = pStart + leftSize + 1;
            int pRightE = pRightS + rightSize;
            node.right = new TreeNode();
            linkLeftAndRight(node.right,indexMap,
                    preorder, pRightS,pRightE,
                    inorder, iRightS, iRightE);
        }
    }
}
