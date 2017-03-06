package com.chen.sort;

import com.chen.heap.BinaryArrayHeap;
import com.chen.utils.RandomUtils;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author ValleyChen
 * @version 1.0.0
 */
public class HeapSorter extends Sorter {

    public HeapSorter(Comparable[] data) {
        super(data);
    }

    @Override
    public void sort() {
        BinaryArrayHeap heap = new BinaryArrayHeap<>(data);
        for (int i = 0; i < data.length; i++) {
            data[i] = heap.pop();
        }
    }

    public static void main(String[] args) {
        for (int k = 0; k < 1000; k++) {
            for (int i = 5; i < 9; i++) {
                int size = 1 << i;
                Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
                new HeapSorter(data).test();
            }
        }
    }
}
