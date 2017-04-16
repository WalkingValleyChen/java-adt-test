package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private List<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo=new int[g.V()];
        for (int v = 0; v < g.V(); ++v) {
            if (!this.marked[v]) {
                this.dfs(g, v);
            }
        }

    }

    private void dfs(Digraph g, int v) {
        this.marked[v] = true;
        onStack[v] = true;
        for (int w : g.adj(v)) {
            if (hasCycle()) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new ArrayList();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.add(x);
                cycle.add(w);
                cycle.add(v);
            }
        }
        onStack[v] = false;

    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public List<Integer> getCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(new In("/tinyDG.txt"));
        System.out.println(graph);
        DirectedCycle dc = new DirectedCycle(graph);
        if(dc.hasCycle()){
            System.out.println(dc.getCycle().stream().map(a->a.toString()).reduce((a,b)->a+"->"+b).get());
        }
    }
}
