package com.chen.graph;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class SymbolGraph {
    private Graph graph;
    private Map<String, Integer> map = new HashMap();
    private String[] keys;

    public SymbolGraph(String stream, String sp) {
        In in = new In(stream);

        while(in.hasNextLine()) {
            String[]  a = in.readLine().split(sp);

            for(int movie = 0; movie < a.length; ++movie) {
                this.map.put(a[movie], Integer.valueOf(this.map.size()));
            }
        }

        this.keys = new String[this.map.size()];

        for (String key:map.keySet()) {
            keys[map.get(key)]=key;
        }

        this.graph = new Graph(this.keys.length);
        in = new In(stream);

        while(in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            String first = a[0];

            for(int i = 1; i < a.length; ++i) {
                this.graph.addEdge(this.map.get(first), this.map.get(a[i]));
            }
        }

    }

    public boolean contains(String key) {
        return this.map.get(key) != null;
    }

    public int index(String key) {
        return this.map.get(key);
    }

    public String name(int v) {
        return this.keys[v];
    }

    public Graph G() {
        return this.graph;
    }

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("/movies.txt", "/");
        System.out.println(sg.G());
        Graph g = sg.G();
        List<Integer> adj = g.adj(sg.index("Jbara, Gregory"));

        for(int i = 0; i < adj.size(); ++i) {
            System.out.println(sg.name(adj.get(i)));
        }

    }
}
