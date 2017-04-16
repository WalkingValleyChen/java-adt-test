package com.chen.graph;

import com.chen.utils.ListUtils;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private List<Integer> pre;
    private List<Integer> post;
    private List<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        pre=new ArrayList<>();
        post=new ArrayList<>();
        for (int v = 0; v < g.V(); ++v) {
            if (!this.marked[v]) {
                this.dfs(g, v);
            }
        }
        reversePost=new ArrayList<>();
        reversePost.addAll(post);
        Collections.reverse(reversePost);

    }

    private void dfs(Digraph g, int v) {
        this.marked[v] = true;
        pre.add(v);
        for (int w : g.adj(v)) {
           if(!marked[w])
               dfs(g,w);
        }
        post.add(v);
    }

    public List<Integer> getPre() {
        return pre;
    }

    public List<Integer> getPost() {
        return post;
    }

    public List<Integer> getReversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph graph = new Digraph(new In("/tinyDAG.txt"));
        System.out.println(graph);

        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        System.out.println(ListUtils.join(dfo.getPre(),","));
        System.out.println(ListUtils.join(dfo.getPost(),","));
        System.out.println(ListUtils.join(dfo.getReversePost(),","));
    }
}
