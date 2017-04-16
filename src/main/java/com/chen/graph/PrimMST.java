package com.chen.graph;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class PrimMST {
    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] distTo;
    private TreeSet<VE> set;
    private VE[] ves;

    private List<Edge> edges;


    public PrimMST(EdgeWeightedGraph g) {
        marked=new boolean[g.V()];
        edgeTo=new Edge[g.V()];
        distTo=new double[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i]=Double.POSITIVE_INFINITY;
        }


        set=new TreeSet<>((a,b)->a.v==b.v?0:a.wight>b.wight?1:-1);
        ves=new VE[g.V()];
        VE first = new VE(0, new Edge(0, 0, 0), 0);
        set.add(first);
        ves[0]=first;
        while (!set.isEmpty()){
            VE ve = set.first();
            set.remove(ve);
            ves[ve.v]=null;
            visit(g,ve.v);

        }

        edges=new ArrayList<>();
        for (int i = 0; i < g.V(); i++) {
            if(edgeTo[i]!=null) edges.add(edgeTo[i]);
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v]=true;
        for(Edge e:g.adj(v)){
            int w = e.other(v);
            if(marked[w])
                continue;
            if(e.weight()<distTo[w]){
                distTo[w]=e.weight();
                edgeTo[w]=e;
                if(ves[w]==null){
                    VE ve = new VE(w, e, e.weight());
                    set.add(ve);
                    ves[w]=ve;
                }else {
                    ves[w].setE(e);
                    ves[w].setWight(e.weight());
                }
            }
        }
    }

    public List<Edge> mstEdges() {
        return edges;
    }





    private class VE{
        private int v;
        private Edge e;
        private double wight;

        public VE(int v, Edge e, double wight) {
            this.v = v;
            this.e = e;
            this.wight = wight;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public Edge getE() {
            return e;
        }

        public void setE(Edge e) {
            this.e = e;
        }

        public double getWight() {
            return wight;
        }

        public void setWight(double wight) {
            this.wight = wight;
        }


    }


    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(new In("/tinyEWG.txt"));
        PrimMST mst = new PrimMST(g);
        List<Edge> edges = mst.mstEdges();
        for(Edge e:edges){
            System.out.println(e.either()+" to "+e.other(e.either())+" "+e.weight());
        }
    }
}
