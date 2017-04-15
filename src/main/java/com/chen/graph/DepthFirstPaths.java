package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edegTo;
    private int count;
    int v;

    public DepthFirstPaths(Graph g, int v) {
        this.marked = new boolean[g.V()];
        this.edegTo = new int[g.V()];
        this.v = v;
        this.dfs(g, v);
    }

    private void dfs(Graph g, int v) {
        this.marked[v] = true;
        ++this.count;
        Iterator var3 = g.adj(v).iterator();

        while(var3.hasNext()) {
            int w = ((Integer)var3.next()).intValue();
            if(!this.marked[w]) {
                this.edegTo[w] = v;
                this.dfs(g, w);
            }
        }

    }

    public boolean hasPathTo(int v) {
        return this.marked[v];
    }

    public int count() {
        return this.count;
    }

    public List<Integer> pathTo(int w) {
        if(!this.hasPathTo(w)) {
            return null;
        } else {
            Stack pathTos = new Stack();

            for(int x = w; this.v != x; x = this.edegTo[x]) {
                pathTos.add(Integer.valueOf(x));
            }

            pathTos.add(Integer.valueOf(this.v));
            return pathTos;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        DepthFirstPaths search = new DepthFirstPaths(graph, 0);
        System.out.println("0 dfs marked count is " + search.count);

        for(int i = 0; i < graph.V(); ++i) {
            System.out.println(" path to " + i + " is " + search.hasPathTo(i));
            List path = search.pathTo(i);
            if(path != null) {
                System.out.println("path is " + (String)path.stream().map((a) -> {
                    return a.toString();
                }).reduce((a, b) -> {
                    return a + "->" + b;
                }).get());
            }
        }

    }
}
