package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class BreadthFirstSearch {
    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(Graph g, int v) {
        this.marked = new boolean[g.V()];
        this.bfs(g, v);
    }

    private void bfs(Graph g, int v) {
        LinkedList queue = new LinkedList();
        queue.add(Integer.valueOf(v));
        this.marked[v] = true;

        while(!queue.isEmpty()) {
            int w = ((Integer)queue.remove()).intValue();
            ++this.count;
            Iterator var5 = g.adj(w).iterator();

            while(var5.hasNext()) {
                int c = ((Integer)var5.next()).intValue();
                if(!this.marked[c]) {
                    this.marked[c] = true;
                    queue.add(Integer.valueOf(c));
                }
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
        BreadthFirstSearch search = new BreadthFirstSearch(graph, 0);
        System.out.println("0 dfs marked count is " + search.count);

        for(int i = 0; i < graph.V(); ++i) {
            System.out.println(i + " marked is " + search.marked(i));
        }

    }
}
