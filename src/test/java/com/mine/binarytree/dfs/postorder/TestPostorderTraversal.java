package com.mine.binarytree.dfs.postorder;

import com.mine.binarytree.SerializeAndDeserializeBT_BFS;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestPostorderTraversal {
    private PostorderTraversal sol = new PostorderTraversal();
    private PostorderTraversal_stack_missing_state sol2 = new PostorderTraversal_stack_missing_state();
    private SerializeAndDeserializeBT_BFS constructor = new SerializeAndDeserializeBT_BFS();

    @Test
    public void testIncompleteAndNonFullBT(){
        assertArrayEquals(new int[]{3,2,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{3,2,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,null,2,3}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testCompleteBT(){
        assertArrayEquals(new int[]{8,4,5,2,6,7,3,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{8,4,5,2,6,7,3,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7,8}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT(){
        assertArrayEquals(new int[]{2,4,3,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{2,4,3,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, null,4}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteBT2(){
        assertArrayEquals(new int[]{4,2,5,3,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{4,2,5,3,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4, null, null,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testIncompleteButFullBT(){
        assertArrayEquals(new int[]{2,4,5,3,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{2,4,5,3,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,null, null, 4,5}
        )).stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testPerfectBT(){
        assertArrayEquals(new int[]{4,5,2,6,7,3,1}, sol.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());

        assertArrayEquals(new int[]{4,5,2,6,7,3,1}, sol2.postorderTraversal(constructor.constructTree(
                new Integer[]{1,2,3,4,5,6,7}
        )).stream().mapToInt(Integer::intValue).toArray());
    }
}
