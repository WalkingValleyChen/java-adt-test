package com.chen.heap;

import com.chen.utils.RandomUtils;
import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Chenwl
 * @version 1.0.0
 * @date 2017/3/6
 */
public class BinaryArrayHeap<T extends Comparable> {

    private T[] data;

    private int size = 0;

    public BinaryArrayHeap(int max) {
        data = (T[]) new Comparable[max + 1];
    }

    public BinaryArrayHeap(T[] data) {
        this.data = (T[]) new Comparable[data.length + 1];
        size = data.length;
        System.arraycopy(data, 0, this.data, 1,
                data.length);
        //下沉
        for (int i = size/2; i > 0; i--) {
            sink(i);
        }
        //上浮
//        for (int i = 1; i <=size; i++) {
//            swin(i);
//        }
    }

    public boolean min(int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    public void swarp(int fo, int to) {
        T temp = data[fo];
        data[fo] = data[to];
        data[to] = temp;
    }

    public void swin(int k) {
        while (k > 1 && min(k, k / 2)) {
            swarp(k, k / 2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= size) {
            int tag = 2 * k;
            if (tag < size && min(tag + 1, tag)) tag++;
            if (min(tag, k)) {
                swarp(tag, k);
                k = tag;
            } else
                return;
        }
    }

    public void check() {
        for (int k = 1; k < size / 2; k++) {
            int tag = 2 * k;
            if (tag < size && min(tag + 1, tag)) tag++;
            if (min(tag, k)) {
                throw new RuntimeException();
            }
        }

    }

    public void insert(T node) {
        data[++size] = node;
        swin(size);
    }

    public T pop() {
        T max = data[1];
        data[1]=data[size--];
        data[size + 1] = null;
        sink(1);
        return max;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {

        for (int x = 0; x < 1000; x++) {


            System.out.println("test insert");
            Random random = new Random();
            BinaryArrayHeap<Integer> heap = new BinaryArrayHeap<>(100);
            for (int i = 100; i > 0; i--) {
                heap.insert(random.nextInt(100));
            }
            heap.check();
            while (heap.getSize() > 0)
                System.out.print(heap.pop() + " ");


            System.out.println();
            System.out.println("----------------------------------");
            System.out.println("test init");
            Integer[] integers = RandomUtils.generateRandomIntegerArray(100, 100);
            BinaryArrayHeap<Integer> heap1 = new BinaryArrayHeap<>(integers);
            heap1.check();
            while (heap1.getSize() > 0)
                System.out.print(heap1.pop() + " ");


        }
    }
}
