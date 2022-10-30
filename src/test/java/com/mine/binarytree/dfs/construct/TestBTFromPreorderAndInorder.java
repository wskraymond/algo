package com.mine.binarytree.dfs.construct;

import com.mine.binarytree.BTConstructor;
import com.mine.binarytree.dfs.inorder.InorderTraversal;
import com.mine.binarytree.dfs.inorder.InorderTraversal_stack;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestBTFromPreorderAndInorder {
    private BTfromPreorderAndInorder sol = new BTfromPreorderAndInorder();

    @Test
    public void test(){
        sol.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }
}
