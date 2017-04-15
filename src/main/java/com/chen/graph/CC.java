package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph g) {
        this.marked = new boolean[g.V()];
        this.id = new int[g.V()];

        for(int v = 0; v < g.V(); ++v) {
            if(!this.marked(v)) {
                ++this.count;
                this.dfs(g, v);
            }
        }

    }

    private void dfs(Graph g, int v) {
        this.marked[v] = true;
        this.id[v] = this.count;
        Iterator var3 = g.adj(v).iterator();

        while(var3.hasNext()) {
            int w = ((Integer)var3.next()).intValue();
            if(!this.marked[w]) {
                this.dfs(g, w);
            }
        }

    }

    public boolean connected(int v, int w) {
        return this.id[v] == this.id[w];
    }

    public int id(int v) {
        return this.id[v];
    }

    public int count() {
        return this.count;
    }

    public boolean marked(int v) {
        return this.marked[v];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        CC cc = new CC(graph);
        System.out.println(cc.count);

        for(int i = 0; i < graph.V(); ++i) {
            System.out.println(i + " id is " + cc.id(i));
        }

    }
}
