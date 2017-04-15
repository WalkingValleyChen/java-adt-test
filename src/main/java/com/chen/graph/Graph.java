package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/15
 */
public class Graph {
    private int V;
    private int E;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new List[V];

        for(int i = 0; i < V; ++i) {
            this.adj[i] = new ArrayList();
        }

    }

    public Graph(In in) {
        this(in.readInt());
        int e = in.readInt();

        for(int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            this.addEdge(v, w);
        }

    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    void addEdge(int v, int w) {
        this.adj[v].add(Integer.valueOf(w));
        this.adj[w].add(Integer.valueOf(v));
        ++this.E;
    }

    List<Integer> adj(int v) {
        return this.adj[v];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Graph{V=" + this.V + ", E=" + this.E + "}\n");

        for(int i = 0; i < this.V; ++i) {
            sb.append(i + " adj:");
            sb.append(String.join(",", (Iterable)this.adj[i].stream().map((a) -> {
                return a.toString();
            }).collect(Collectors.toList())));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In("/tinyG.txt"));
        System.out.println(graph);
    }
}
