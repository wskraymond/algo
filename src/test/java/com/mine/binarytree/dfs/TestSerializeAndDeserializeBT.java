package com.mine.binarytree.dfs;

import com.mine.binarytree.SerializeAndDeserializeBT_BFS;
import com.mine.binarytree.SerializeAndDeserializeBT_DFS;
import org.junit.Assert;
import org.junit.Test;

public class TestSerializeAndDeserializeBT {
    private SerializeAndDeserializeBT_BFS sol = new SerializeAndDeserializeBT_BFS();
    private SerializeAndDeserializeBT_DFS sol2 = new SerializeAndDeserializeBT_DFS();

    @Test
    public void test(){
        Assert.assertEquals("1,2,3,null,null,4,5,null,null,null,null",
                sol.serialize(sol.constructTree(new Integer[]{1,2,3,null, null, 4,5,null,null,null,null})));
    }

    @Test
    public void test2(){
        Assert.assertEquals("1,2,3,null,null,4,5,null,null,null,null",
                sol.serialize(sol.deserialize("1,2,3,null,null,4,5,null,null,null,null")));
        Assert.assertEquals("1,2,null,null,3,4,null,null,5,null,null",
                sol2.serialize(sol2.deserialize("1,2,null,null,3,4,null,null,5,null,null")));
    }
}
