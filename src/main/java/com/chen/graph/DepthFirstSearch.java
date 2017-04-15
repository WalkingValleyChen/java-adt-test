package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int v) {
        this.marked = new boolean[g.V()];
        this.dfs(g, v);
    }

    private void dfs(Graph g, int v) {
        this.marked[v] = true;
        ++this.count;
        Iterator var3 = g.adj(v).iterator();

        while(var3.hasNext()) {
            int w = ((Integer)var3.next()).intValue();
            if(!this.marked[w]) {
                this.dfs(g, w);
            }
        }

    }

    public boolean marked(int v) {
        return this.marked[v];
    }

    public int count() {
        return this.count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);
        System.out.println("0 dfs marked count is " + search.count);

        for(int i = 0; i < graph.V(); ++i) {
            System.out.println(i + " marked is " + search.marked(i));
        }

    }
}
