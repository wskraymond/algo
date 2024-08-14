package com.mine.graph.undirected.unionfind;

public class CountComponents {
    class UnionFind{
        int[] p;
        UnionFind(int n){
            p = new int[n];
            for(int i=0;i<n;i++){
                p[i] = i;
            }
        }

        public int find(int x){
            return p[x]==x ? x : (p[x] = find(p[x]));
        }

        public void union(int x, int y){
            p[find(x)]=find(y);
        }
    }
    /**
     * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
     *
     * Return the number of connected components in the graph.
     */
    public int countComponents(int n, int[][] edges) {
        UnionFind u = new UnionFind(n);
        for(int[] edge:edges){
            u.union(edge[0], edge[1]);
        }

        int count=0;
        for(int i=0;i<n;i++){
            if(i==u.find(i)){
                count++;
            }
        }

        return count;
    }
}
