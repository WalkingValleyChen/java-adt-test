package com.chen.graph;

import com.chen.heap.BinaryArrayHeap;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class KruskalMST {

    private BinaryArrayHeap<Edge> heap;
    private boolean[] marked;
    private int[] tree;
    private int index;
    private List<Edge> edges;

    public KruskalMST(EdgeWeightedGraph g) {
        heap = new BinaryArrayHeap(g.E()*2);
        marked = new boolean[g.V()];
        tree = new int[g.V()];
        edges=new ArrayList<>();

        for (int v = 0; v < g.V(); v++) {
            for (Edge e : g.adj(v))
                heap.insert(e);
        }

        while (heap.getSize() != 0&&edges.size()<g.V()-1) {
            Edge e = heap.pop();
            int v = e.either();
            int w = e.other(v);

            if (marked[v] && marked[w] && tree[v] != tree[w]) {
                int value = tree[w];
                for (int i = 0; i < tree.length; i++) {
                    if(tree[i]==value) tree[i]=tree[v];
                }
                edges.add(e);
            }else if(marked[v]&&!marked[w]){
                tree[w]=tree[v];
                edges.add(e);
            }else if(marked[w]&&!marked[v]){
                tree[v]=tree[w];
                edges.add(e);
            }else if(!marked[v] && !marked[w]){
                index++;
                tree[v]=index;
                tree[w]=index;
                edges.add(e);
            }

            marked[v]=true;
            marked[w]=true;
        }

    }
    public List<Edge> mstEdges() {
        return edges;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(new In("/tinyEWD.txt"));
        KruskalMST mst = new KruskalMST(g);
        List<Edge> edges = mst.mstEdges();
        for(Edge e:edges){
            System.out.println(e.either()+" to "+e.other(e.either())+" "+e.weight());
        }
    }
}
