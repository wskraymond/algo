package com.mine.binarytree.dfs.construct;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestBTFromPreorderAndInorder {
    private BTfromPreorderAndInorder_linear_start_end sol = new BTfromPreorderAndInorder_linear_start_end();

    @Test
    public void test(){
        sol.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }
}
