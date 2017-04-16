package com.chen.graph;

import com.chen.utils.ListUtils;
import edu.princeton.cs.algs4.In;

import java.util.List;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class Topological {

    private List<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle dc=new DirectedCycle(g);
        if(!dc.hasCycle()){
            DepthFirstOrder dfo=new DepthFirstOrder(g);
            order=dfo.getReversePost();
        }
    }

    public List<Integer> getOrder() {
        return order;
    }

    public boolean isDAG(){
        return order!=null;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(new In("/tinyDAG.txt"));
        System.out.println(graph);

        Topological topological = new Topological(graph);
        if(topological.isDAG())
            System.out.println(ListUtils.join(topological.getOrder(),","));
    }
}
