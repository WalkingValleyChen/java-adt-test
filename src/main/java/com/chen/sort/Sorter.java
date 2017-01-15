package com.chen.sort;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public abstract class Sorter<T extends Comparable> {

    T[] data;

    public Sorter() {
    }

    public Sorter(T[] data) {
        this.data = data;
    }

    public abstract void sort();

    public void swarp(int fo, int to) {
        T temp = data[fo];
        data[fo] = data[to];
        data[to] = temp;
    }

    public boolean gt(T fo, T to) {
        return fo.compareTo(to) > 0;
    }

    public boolean gtEQ(T fo, T to) {
        return fo.compareTo(to) >= 0;
    }

    public boolean lt(T fo, T to) {
        return fo.compareTo(to) < 0;
    }

    public boolean ltOrEq(T fo, T to) {
        return fo.compareTo(to) <= 0;
    }

    public boolean check() {
        for (int i = 0; i < data.length - 2; i++) {
            if (!ltOrEq(data[i], data[i + 1]))
                return false;
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < data.length - 1; i++) {
            System.out.print(data[i] + ",");
        }
        System.out.print(data[data.length - 1]);
        System.out.println();
    }

    public void test() {
        sort();
        if (check())
            print();
        else
            throw new RuntimeException();

    }

}
