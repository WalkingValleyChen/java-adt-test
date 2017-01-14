package com.chen.sort;

import com.chen.utils.RandomUtils;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class InsertSorter extends Sorter {

    public InsertSorter(Comparable[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j >0 && lt(data[j],data[j-1]) ; j--) {
                    swarp(j,j-1);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new InsertSorter(data).test();
        }

    }
}
