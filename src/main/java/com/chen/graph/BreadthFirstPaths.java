package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edegTo;
    private int count;
    int v;

    public BreadthFirstPaths(Graph g, int v) {
        this.marked = new boolean[g.V()];
        this.edegTo = new int[g.V()];
        this.v = v;
        this.bfs(g, v);
    }

    private void bfs(Graph g, int s) {
        LinkedList queue = new LinkedList();
        queue.add(Integer.valueOf(s));
        this.marked[s] = true;

        while(!queue.isEmpty()) {
            int v = ((Integer)queue.remove()).intValue();
            ++this.count;
            Iterator var5 = g.adj(v).iterator();

            while(var5.hasNext()) {
                int w = ((Integer)var5.next()).intValue();
                if(!this.marked[w]) {
                    this.marked[w] = true;
                    this.edegTo[w] = v;
                    queue.add(Integer.valueOf(w));
                }
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
        BreadthFirstPaths search = new BreadthFirstPaths(graph, 0);
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
