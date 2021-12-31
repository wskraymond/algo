package com.mine.binarytree.dfs.inorder;

import com.mine.binarytree.TreeNode;

import java.util.*;

public class InorderTraversal_stack {
    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     *
     * Example 1:
     *
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
     *
     * Example 2:
     *
     * Input: root = []
     * Output: []
     *
     * Example 3:
     *
     * Input: root = [1]
     * Output: [1]
     *
     * Constraints:
     *
     *     The number of nodes in the tree is in the range [0, 100].
     *     -100 <= Node.val <= 100
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        /**
         * ArrayList: list
         * LinkedList: list, queue, deque
         * ArrayDeque: Deque
         *     - They are not thread-safe;
         *     - in the absence of external synchronization,
         *          they do not support concurrent access by multiple threads.
         *     - Null elements are prohibited.
         *     - This class is likely to be
         *          - faster than Stack when used as a stack,
         *          - and faster than LinkedList when used as a queue.
         *              - circularity: circular Array !!!
         *
         * Notes:
         * While Deque implementations are not strictly required to prohibit the insertion of null elements,
         * they are strongly encouraged to do so.
         * Users of any Deque implementations that do allow null elements are strongly encouraged not to take
         * advantage of the ability to insert nulls.
         * This is so because null is used as a special return value by various methods
         * to indicated that the deque is empty.
         *
         * stack(LIFO): push() & pop() & peek()
         * queue(FIFO): offer() & poll() & peek()
         * (throw exception) : add() & remove() & element()
         *
         * dfs(Node param) = {dfs(param.left),
         *                      visit(param),
         *                    dfs(param.right)}
         */

        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode param = root;
        while (param!=null || !stack.isEmpty()){ //if param==null or end of return....
            while(param!=null){     //if param==null , then return
                stack.push(param);  //enter function: save param
                param = param.left; //input param: dfs(param.left)
            }
            param = stack.pop();    //return function: restore param
            result.add(param.val);  //visit(param)
            param = param.right;    //input param: dfs(param.right)
        }
        return result;
    }
}

