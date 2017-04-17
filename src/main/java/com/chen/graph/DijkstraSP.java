package com.chen.graph;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/17
 */
public class DijkstraSP {

    private DirectedEdge edgeTo[];
    private Double[] distTo;
    private IndexMinPQ<Double> pq;
    private int v;

    public DijkstraSP(EdgeWeightedDigraph g, int v) {
        this.v = v;
        edgeTo = new DirectedEdge[g.V()];
        distTo = new Double[g.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(g.V());


        distTo[v] = 0D;
        pq.insert(v, 0D);

        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }

    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[v] + e.weight() < distTo[w]) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int w) {
        return distTo[w];
    }

    public boolean hasPathTo(int w) {
        return distTo[w] < Double.POSITIVE_INFINITY;
    }

    public List<DirectedEdge> pathTo(int w) {
        if (!hasPathTo(w)) return null;
        LinkedList<DirectedEdge> edges = new LinkedList<>();
        for (int x = w; x != v; x = edgeTo[x].from()) {
            edges.addFirst(edgeTo[x]);
        }
        return edges;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("/tinyEWD.txt"));
        DijkstraSP sp = new DijkstraSP(g, 0);

        for (int i = 0; i < g.V(); i++) {
            System.out.println(sp.v + " to " + i + " " + sp.distTo(i));
            List<DirectedEdge> edges = sp.pathTo(i);
            if (edges == null || edges.isEmpty())
                System.out.println("no path");
            else
                System.out.println("path is " + sp.pathTo(i).stream().map(a -> a.from() + "->" + a.to() + ":" + a.weight()).reduce((a, b) -> a + " " + b).get());
        }
    }
}
