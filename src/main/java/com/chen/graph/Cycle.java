package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g) {
        this.marked = new boolean[g.V()];

        for(int v = 0; v < g.V(); ++v) {
            if(!this.marked[v]) {
                this.dfs(g, v, v);
            }
        }

    }

    private void dfs(Graph g, int v, int p) {
        this.marked[v] = true;
        Iterator var4 = g.adj(v).iterator();

        while(var4.hasNext()) {
            int w = ((Integer)var4.next()).intValue();
            if(!this.marked[w]) {
                this.dfs(g, w, v);
            } else if(w != p) {
                this.hasCycle = true;
            }
        }

    }

    public boolean hasCycle() {
        return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.hasCycle());
    }
}
