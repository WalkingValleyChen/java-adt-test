package com.chen.graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class LazyPrimMST {
    private boolean[] marked;
    private MinPQ<Edge> minPQ;
    private Queue<Edge> mst;

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked=new boolean[g.V()];
        minPQ=new MinPQ<>();
        mst=new LinkedList<>();

        visit(g,0);
        while (!minPQ.isEmpty()){
            Edge e = minPQ.delMin();
            int v = e.either();
            int w = e.other(v);
            if(marked[v]&&marked[w])
                continue;
            else if(!marked[v]){
                mst.add(e);
                visit(g,v);
            }
            else if(!marked[w]){
                mst.add(e);
                visit(g,w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v]=true;

        for(Edge e:g.adj(v)){
            int w = e.other(v);
            if(!marked[w]){
                minPQ.insert(e);
            }
        }
    }

    public Queue<Edge> mstEdges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(new In("/tinyEWG.txt"));
        LazyPrimMST mst = new LazyPrimMST(g);
        Queue<Edge> edges = mst.mstEdges();
        while (!edges.isEmpty()){
            Edge e = edges.remove();
            System.out.println(e.either()+" to "+e.other(e.either())+" "+e.weight());
        }
    }
}
