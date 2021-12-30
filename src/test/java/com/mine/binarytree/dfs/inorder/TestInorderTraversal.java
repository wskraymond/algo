package com.mine.binarytree.dfs.inorder;

import com.mine.binarytree.BTConstructor;
import com.mine.binarytree.dfs.inorder.InorderTraversal;
import com.mine.binarytree.dfs.inorder.InorderTraversal_stack;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestInorderTraversal {
    private InorderTraversal sol = new InorderTraversal();
    private InorderTraversal_stack sol2 = new InorderTraversal_stack();
    private BTConstructor constructor = new BTConstructor();

    @Test
    public void testIncompleteAndNonFullBT(){
        assertArrayEquals(new int[]{1,3,2}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,3,2}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testCompleteBT(){
        assertArrayEquals(new int[]{8,4,2,5,1,6,3,7}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{8,4,2,5,1,6,3,7}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT(){
        assertArrayEquals(new int[]{2,1,3,4}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{2,1,3,4}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT2(){
        assertArrayEquals(new int[]{4,2,1,3,5}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{4,2,1,3,5}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteButFullBT(){
        assertArrayEquals(new int[]{2,1,4,3,5}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{2,1,4,3,5}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testPerfectBT(){
        assertArrayEquals(new int[]{4,2,5,1,6,3,7}, sol.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{4,2,5,1,6,3,7}, sol2.inorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());
    }
}
