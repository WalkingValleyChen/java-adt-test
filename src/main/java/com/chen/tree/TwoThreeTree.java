package com.chen.tree;

import com.chen.utils.RandomUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Chenwl
 * @version 1.0.0
 * @date 2017/3/8
 */
public class TwoThreeTree<T extends Comparable> implements Tree<T> {

    private Node root;

    @Override
    public void add(T value) {
        if (root == null)
            root = new Node(value, null);
        else{
            Node4 upNode4 = add(null, root, value);
            if(upNode4!=null){
                root=new Node(upNode4.twoValue,null);
                root.oneChild=getSplitNode4Left(upNode4);
                root.twoChild=getSplitNode4Right(upNode4);
            }
        }
    }

    private Node4 add(Node parent, Node node, T value) {
        if (value == null) return null;

        //当前是叶子节点
        if (node.isLeaf()) {
            if (node.isThree()) {
                return threeLeafNodeToFour(node,value);
            } else {
                if (value.compareTo(node.oneValue) < 0) {
                    node.twoValue=node.oneValue;
                    node.oneValue=value;
                }else {
                    node.twoValue=value;
                }
            }
        } else {

            Node4 upNode4;
            if (node.isThree()) {
                //当前不是叶子节点，增加到子节点中去
                if (value.compareTo(node.oneValue) < 0) {

                    if (node.oneChild == null)
                        node.oneChild = new Node(value, node);
                    else{
                        upNode4 = add(node, node.oneChild, value);
                        if(upNode4!=null){
                            Node4 node4 = new Node4(upNode4.twoValue, node.oneValue, node.twoValue, node.parent);
                            node4.oneChild=getSplitNode4Left(upNode4);
                            node4.twoChild=getSplitNode4Right(upNode4);
                            node4.threeChild=node.twoChild;
                            node4.fourChild=node.threeChild;
                            return node4;
                        }
                    }
                } else if (value.compareTo(node.twoValue) < 0) {
                    if (node.twoChild == null)
                        node.twoChild = new Node(value, node);
                    else{
                        upNode4=add(node, node.twoChild, value);
                        if(upNode4!=null){
                            Node4 node4 = new Node4( node.oneValue,upNode4.twoValue, node.twoValue, node.parent);
                            node4.oneChild=node.oneChild;
                            node4.twoChild=getSplitNode4Left(upNode4);
                            node4.threeChild=getSplitNode4Right(upNode4);
                            node4.fourChild=node.threeChild;
                            return node4;
                        }
                    }
                } else {
                    if (node.threeChild == null)
                        node.threeChild = new Node(value, node);
                    else{
                        upNode4=add(node, node.threeChild, value);
                        if(upNode4!=null){
                            Node4 node4 = new Node4( node.oneValue, node.twoValue,upNode4.twoValue, node.parent);
                            node4.oneChild=node.oneChild;
                            node4.twoChild=node.twoChild;
                            node4.threeChild=getSplitNode4Left(upNode4);
                            node4.fourChild=getSplitNode4Right(upNode4);
                            return node4;
                        }
                    }
                }
            } else {
                if (value.compareTo(node.oneValue) < 0) {
                    if (node.oneChild == null)
                        node.oneChild = new Node(value, node);
                    else{
                        upNode4=add(node, node.oneChild, value);
                        if(upNode4!=null){
                            node.twoValue=node.oneValue;
                            node.oneValue=upNode4.twoValue;

                            node.threeChild=node.twoChild;
                            node.oneChild=getSplitNode4Left(upNode4);
                            node.twoChild=getSplitNode4Right(upNode4);
                        }
                    }
                } else {
                    if (node.twoChild == null)
                        node.twoChild = new Node(value, node);
                    else{
                        upNode4=add(node, node.twoChild, value);
                        if(upNode4!=null){
                            node.twoValue=upNode4.twoValue;

                            node.twoChild=getSplitNode4Left(upNode4);
                            node.threeChild=getSplitNode4Right(upNode4);
                        }
                    }
                }
            }
        }

        return null;
    }

    private Node getSplitNode4Right(Node4 upNode4) {
        return new Node(upNode4.threeValue,upNode4.threeChild,upNode4.fourChild,upNode4.parent);
    }

    private Node getSplitNode4Left(Node4 upNode4) {
        return new Node(upNode4.oneValue,upNode4.oneChild,upNode4.twoChild,upNode4.parent);
    }

    private Node4 threeLeafNodeToFour(Node node, T value) {
        Node4 node4=null;
        if(value.compareTo(node.oneValue)<0){
            node4=new Node4(value,node.oneValue,node.twoValue,node.parent);
        }else if(value.compareTo(node.twoValue)<0){
            node4=new Node4(node.oneValue,value,node.twoValue,node.parent);
        }else {
            node4=new Node4(node.oneValue,node.twoValue,value,node.parent);
        }
//        if(node.oneChild!=null&&node.oneChild.maxValue().compareTo(node4.oneValue)<0){
//            node4.oneChild=node.oneChild;
//        }else {
//            node4.twoChild=node.oneChild;
//        }
//
//        if(node.twoChild!=null&&node.twoChild.maxValue().compareTo(node4.twoValue)<0){
//            node4.twoChild=node.twoChild;
//        }else {
//            node4.threeChild=node.twoChild;
//        }
//
//        if(node.threeChild!=null&&node.threeChild.maxValue().compareTo(node4.threeChild)<0){
//            node4.threeChild=node.threeChild;
//        }else {
//            node4.fourChild=node.threeChild;
//        }
        return node4;
    }


    @Override
    public void delete(T value) {
        throw new UnsupportedOperationException("未实现删除方法");
    }

    @Override
    public boolean contains(T value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void print() {
        print(root);
    }

    private void print(Node node) {
        if(node==null) return;
        print(node.oneChild);
        if(node.oneValue!=null)
            System.out.printf(node.oneValue+" ");
        print(node.twoChild);
        if(node.twoValue!=null)
            System.out.printf(node.twoValue+" ");
        print(node.threeChild);
    }

    @Override
    public void levelPrint() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> queue1 = new LinkedList<Node>();
        queue.offer(root);// 从根节点入队列
        while (!queue.isEmpty()) {// 在队列为空前反复迭代
            Node node = queue.poll();// 取出队列首节点
            if(node.isThree()){
                System.out.printf(node.oneValue.toString()+"|"+node.twoValue.toString() + " ");
            }else {
                System.out.printf(node.oneValue.toString() + " ");
            }

            if (node.oneChild != null)
                queue1.offer(node.oneChild);
            if (node.twoChild != null)
                queue1.offer(node.twoChild);
            if (node.threeChild != null)
                queue1.offer(node.threeChild);
            if (queue.isEmpty()) {
                System.out.println();
                queue = queue1;
                queue1 = new LinkedList<Node>();
            }
        }
    }

    @Override
    public void check() {

    }

    class Node {
        T oneValue;
        T twoValue;

        Node oneChild;
        Node twoChild;
        Node threeChild;
        Node parent;

        public Node() {
        }

        public Node(T one, Node parent) {
            this.oneValue = one;
            this.parent = parent;
        }

        public Node(T oneValue, Node oneChild, Node twoChild, Node parent) {
            this.oneValue = oneValue;
            this.oneChild = oneChild;
            this.twoChild = twoChild;
            this.parent = parent;
        }

        public boolean isThree() {
            return oneValue != null && twoValue != null;
        }

        public boolean isLeaf() {
            return oneChild == null && twoChild == null && threeChild == null;
        }

        public T maxValue(){
            if(twoValue!=null)
                return twoValue;
            else
                return oneValue;
        }
    }

    class Node4 {
        T oneValue;
        T twoValue;
        T threeValue;

        Node oneChild;
        Node twoChild;
        Node threeChild;
        Node fourChild;
        Node parent;

        public Node4(T oneValue, T twoValue, T threeValue, Node parent) {
            this.oneValue = oneValue;
            this.twoValue = twoValue;
            this.threeValue = threeValue;
            this.parent = parent;
        }
    }


    public static void main(String[] args) {
        Integer[] integers = RandomUtils.generateRandomIntegerArray(20, 20);
        TwoThreeTree<Integer> tree = new TwoThreeTree<>();
        for (Integer i : integers) {
            tree.add(i);
            tree.levelPrint();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        }

        tree.print();
    }
}
