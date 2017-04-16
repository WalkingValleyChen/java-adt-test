package com.chen.graph;

import edu.princeton.cs.algs4.In;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph g) {
        this.marked = new boolean[g.V()];
        this.id = new int[g.V()];

        DepthFirstOrder dfo = new DepthFirstOrder(g.reverse());
        for (int v : dfo.getReversePost())
            if (!this.marked(v)) {
                ++this.count;
                this.dfs(g, v);
            }

    }

    private void dfs(Digraph g, int v) {
        this.marked[v] = true;
        this.id[v] = this.count;
        for (int w : g.adj(v))
            if (!this.marked[w]) {
                this.dfs(g, w);
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
        Digraph graph = new Digraph(new In("/tinyDG.txt"));
        System.out.println(graph);
        KosarajuSCC cc = new KosarajuSCC(graph);
        System.out.println(cc.count);

        for (int i = 0; i < graph.V(); ++i) {
            System.out.println(i + " id is " + cc.id(i));
        }

    }
}
