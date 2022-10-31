package com.mine.binarytree.dfs.preorder;

import com.mine.binarytree.SerializeAndDeserializeBT_BFS;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestPreorderTraversal {
    private PreorderTraversal sol = new PreorderTraversal();
    private PreorderTraversal_stack sol2 = new PreorderTraversal_stack();
    private SerializeAndDeserializeBT_BFS constructor = new SerializeAndDeserializeBT_BFS();

    @Test
    public void testIncompleteAndNonFullBT(){
        assertArrayEquals(new int[]{1,2,3}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,3}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testCompleteBT(){
        assertArrayEquals(new int[]{1,2,4,8,5,3,6,7}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,4,8,5,3,6,7}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT(){
        assertArrayEquals(new int[]{1,2,3,4}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,3,4}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT2(){
        assertArrayEquals(new int[]{1,2,4,3,5}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,4,3,5}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteButFullBT(){
        assertArrayEquals(new int[]{1,2,3,4,5}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,3,4,5}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testPerfectBT(){
        assertArrayEquals(new int[]{1,2,4,5,3,6,7}, sol.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{1,2,4,5,3,6,7}, sol2.preorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());
    }
}
