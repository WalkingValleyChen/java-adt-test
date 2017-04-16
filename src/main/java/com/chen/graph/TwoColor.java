package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph g) {
        this.marked = new boolean[g.V()];
        this.color = new boolean[g.V()];

        for(int v = 0; v < g.V(); ++v) {
            if(!this.marked[v]) {
                this.dfs(g, v);
            }
        }

    }

    private void dfs(Graph g, int v) {
        this.marked[v] = true;
        for(int w:g.adj(v))
            if(!this.marked[w]) {
                this.color[w] = !this.color[v];
                this.dfs(g, w);
            } else if(this.color[w] != this.color[v]) {
                this.isTwoColor = false;
            }

    }

    public boolean isTwoColor() {
        return this.isTwoColor;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        TwoColor tc = new TwoColor(graph);
        System.out.println(tc.isTwoColor());
    }
}
