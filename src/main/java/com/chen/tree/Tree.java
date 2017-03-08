package com.chen.tree;

/**
 * @author Chenwl
 * @version 1.0.0
 * @date 2017/3/7
 */
public interface Tree<T extends Comparable> {

    void add(T value);

    void delete(T value);

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void print();

    void check();
}
