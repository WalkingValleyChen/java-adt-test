package com.chen.tree;

import com.chen.utils.RandomUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Chenwl
 * @version 1.0.0
 * @date 2017/3/7
 */
public class BinaryTree<T extends Comparable> implements Tree<T> {

    private Node root;

    @Override
    public void add(T value) {
        root = add(root, value);
    }

    private Node add(Node node, T value) {
        if (value == null) return node;
        if (node != null) {
            node.size += 1;
            if (value.compareTo(node.value) > 0) {
                node.right = add(node.right, value);
            } else if (value.compareTo(node.value) < 0) {
                node.left = add(node.left, value);
            } else {
                if (node.hashCode() % 2 == 0) {
                    node.right = add(node.right, value);
                } else {
                    node.left = add(node.left, value);
                }

            }

        } else
            node = new Node(value);
        return node;
    }

    @Override
    public void delete(T value) {
        root = delete(root, value);
    }

    private Node delete(Node node, T value) {
        if (node == null) return null;
        int i = value.compareTo(node.value);
        if (i == 0) {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node temp=node;
            node= min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        } else if (i > 0) {
            node.right = delete(node.right, value);
        } else {
            node.left = delete(node.left, value);
        }
        node.size = sise(node.left) + sise(node.right) + 1;
        return node;
    }

    private void deleteMin() {
        root=deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if(node.left==null)
            return node.right;
        node.left=deleteMin(node.left);
        node.size=sise(node.left)+sise(node.right)+1;
        return node;
    }

    private Node min(Node node) {
        if(node.left==null)
            return node;
        return min(node.left);
    }

    @Override
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node node, T value) {
        if (node == null) return false;
        int i = value.compareTo(node.value);
        if (i == 0) return true;
        else if (i > 0)
            return contains(node.right, value);
        else
            return contains(node.left, value);
    }

    @Override
    public int size() {
        return sise(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void print() {
        print(root);
        System.out.println();
    }

    @Override
    public void check() {
        check(root);
    }

    private void check(Node node) {
        if (sise(node) != (sise(node.left) + sise(node.right)) + 1) {
            throw new RuntimeException("树节点" + node.value + "数目不对");
        }
        if (node.left!=null&&node.value.compareTo(node.left.value) < 0) {
            throw new RuntimeException("树节点" + node.value + "比左节点小");
        }
        if (node.right!=null&&node.value.compareTo(node.right.value) > 0) {
            throw new RuntimeException("树节点" + node.value + "比右节点大");
        }
        if (node.left != null)
            check(node.left);
        if(node.right!=null)
            check(node.right);
    }

    public void print(Node node) {
        if (node == null) return;
        print(node.left);
        System.out.printf(node.value.toString() + "(" + node.size + ")" + " ");
        print(node.right);
    }

    int sise(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    public void levelPrint() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> queue1 = new LinkedList<Node>();
        queue.offer(root);// 从根节点入队列
        while (!queue.isEmpty()) {// 在队列为空前反复迭代
            Node node = queue.poll();// 取出队列首节点
            System.out.printf(node.value.toString() + "(" + sise(node)+") ");
            if (node.left != null)
                queue1.offer(node.left);// 左孩子入列
            if (node.right != null)
                queue1.offer(node.right);// 右孩子入列
            if (queue.isEmpty()) {
                System.out.println();
                queue = queue1;
                queue1 = new LinkedList<Node>();
            }
        }
    }

    private void levelPrint(AVLTree.Node node, List<AVLTree.Node> queue) {
        queue.add(node);
        if (node != null) {
            levelPrint(node.left, queue);
            levelPrint(node.right, queue);
        }
    }

    class Node {
        Node left;
        Node right;
        T value;
        int size;

        public Node(T value) {
            this.value = value;
            this.size = 1;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = RandomUtils.generateRandomIntegerArray(100, 100);
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (Integer i : integers) {
            tree.add(i);
        }
        tree.levelPrint();
        tree.check();

        for (int i = 0; i < 10; i++) {
            System.out.println("删除" + integers[i]);
            tree.delete(integers[i]);
        }
        tree.levelPrint();
        tree.check();
        for (int i = 0; i < 10; i++) {
            System.out.println("包含" + integers[i] + ":" + tree.contains(integers[i]));
        }


    }

}
