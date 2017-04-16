package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.*;

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
        Queue<Integer> queue = new LinkedList();
        queue.add(s);
        this.marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            count++;
            for (int w : g.adj(v))
                if (!this.marked[w]) {
                    this.marked[w] = true;
                    this.edegTo[w] = v;
                    queue.add(w);
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
        if (!this.hasPathTo(w)) {
            return null;
        } else {
            Stack pathTos = new Stack();

            for (int x = w; this.v != x; x = this.edegTo[x]) {
                pathTos.add(Integer.valueOf(x));
            }

            pathTos.add(v);
            return pathTos;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
        BreadthFirstPaths search = new BreadthFirstPaths(graph, 0);
        System.out.println("0 dfs marked count is " + search.count);

        for (int i = 0; i < graph.V(); ++i) {
            System.out.println(" path to " + i + " is " + search.hasPathTo(i));
            List path = search.pathTo(i);
            if (path != null) {
                System.out.println("path is " + path.stream().map(a -> a.toString()).reduce((a, b) -> a + "->" + b).get());
            }
        }

    }
}
