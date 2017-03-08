package com.chen.tree;

import com.chen.utils.RandomUtils;

import java.util.*;

/**
 * @author Chenwl
 * @version 1.0.0
 * @date 2017/3/8
 */
public class AVLTree<T extends Comparable> implements Tree<T> {
    private Node root;

    @Override
    public void add(T value) {
        root = add(root, value);
    }

    private Node add(Node node, T value) {
        if (value == null) return node;
        if (node != null) {
            if (value.compareTo(node.value) > 0) {
                node.right = add(node.right, value);
                if (height(node.right) - height(node.left) > 1) {
                    if (value.compareTo(node.right.value) > 0) {
                        node = rolateLeft(node);
                    } else {
                        node = rolateRightLeft(node);
                    }
                }
            } else {
                node.left = add(node.left, value);
                if (height(node.left) - height(node.right) > 1) {
                    if (value.compareTo(node.left.value) > 0) {
                        node = rolateLeftRight(node);
                    } else {
                        node = rolateRight(node);
                    }
                }
            }

        } else
            node = new Node(value);
        node.size = sise(node.left) + sise(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private Node rolateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        node.size = sise(node.left) + sise(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.size = sise(left.left) + sise(left.right) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;
        return left;
    }

    private Node rolateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        node.size = sise(node.left) + sise(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.size = sise(right.left) + sise(right.right) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;
        return right;
    }

    private Node rolateRightLeft(Node node) {
        node.right = rolateRight(node.right);
        return rolateLeft(node);
    }

    private Node rolateLeftRight(Node node) {
        node.left = rolateLeft(node.left);
        return rolateRight(node);
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
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;

            node = deleteRightBalance(node);
        } else if (i > 0) {
            node.right = delete(node.right, value);
            node = deleteRightBalance(node);
        } else {
            node.left = delete(node.left, value);
            node = deleteLeftBalance(node);
        }
        node.size = sise(node.left) + sise(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }



    private Node deleteMin(Node node) {
        node = deleteMin0(node);
        node = deleteLeftBalance(node);
        return node;
    }

    private Node deleteMin0(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.size = sise(node.left) + sise(node.right) + 1;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        node = deleteLeftBalance(node);
        return node;
    }

    /**
     * 删除左侧节点后平衡
     *
     * @param node
     * @return
     */
    private Node deleteLeftBalance(Node node) {
        if (node != null && height(node.right) - height(node.left) > 1) {

            if (height(node.right.left) > height(node.right.right)) {
                node = rolateRightLeft(node);
            } else {
                node = rolateLeft(node);
            }
        }
        return node;
    }

    /**
     * 删除右侧节点后平衡
     *
     * @param node
     * @return
     */
    private Node deleteRightBalance(Node node) {
        if (height(node.left) - height(node.right) > 1) {

            if (height(node.left.right) > height(node.left.left)) {
                node = rolateLeftRight(node);
            } else {
                node = rolateRight(node);
            }

        }
        return node;
    }

    private Node min(Node node) {
        if (node.left == null)
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

    public void levelPrint() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> queue1 = new LinkedList<Node>();
        queue.offer(root);// 从根节点入队列
        while (!queue.isEmpty()) {// 在队列为空前反复迭代
            Node node = queue.poll();// 取出队列首节点
            System.out.printf(node.value.toString() + "(" + height(node.left) + "|" + height(node.right) + ") ");
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

    private void levelPrint(Node node, List<Node> queue) {
        queue.add(node);
        if (node != null) {
            levelPrint(node.left, queue);
            levelPrint(node.right, queue);
        }
    }

    @Override
    public void check() {
        check(root);
    }

    private void check(Node node) {
        if (node == null)
            return;
        if (height(node) != Math.max(height(node.left), height(node.right)) + 1) {
            throw new RuntimeException("树节点" + node.value + "高度不对");
        }
        if (Math.abs(height(node.left) - height(node.right)) > 1) {
            throw new RuntimeException("树节点" + node.value + "高度差不对");
        }
        if (sise(node) != (sise(node.left) + sise(node.right)) + 1) {
            throw new RuntimeException("树节点" + node.value + "数目不对");
        }
        if (node.left != null && node.value.compareTo(node.left.value) < 0) {
            throw new RuntimeException("树节点" + node.value + "比左节点小");
        }
        if (node.right != null && node.value.compareTo(node.right.value) > 0) {
            throw new RuntimeException("树节点" + node.value + "比右节点大");
        }
        if (node.left != null)
            check(node.left);
        if (node.right != null)
            check(node.right);

    }

    public void print(Node node) {
        if (node == null) return;
        print(node.left);
        System.out.printf(node.value.toString() + "(" + node.size + "_" + node.height + ")" + " ");
        print(node.right);
    }

    int sise(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    int height(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    class Node {
        Node left;
        Node right;
        T value;
        int size;
        int height;

        public Node(T value) {
            this.value = value;
            this.height = 0;
            size = 1;
        }
    }

    public static void main(String[] args) {
        Integer[] integers = RandomUtils.generateRandomIntegerArray(100, 100);
        AVLTree<Integer> tree = new AVLTree<>();
        for (Integer i : integers) {
            tree.add(i);
        }
        tree.levelPrint();
        tree.check();

        Arrays.sort(integers);
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
