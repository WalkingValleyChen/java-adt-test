package com.chen.sort;

import com.chen.utils.RandomUtils;

/**
 * 选择排序
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class SelectSorter extends Sorter {

    public SelectSorter(Comparable[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int i = 0; i < data.length; i++) {
            int min=i;
            for (int j = i+1; j < data.length; j++) {
                if(lt(data[j],data[min]))
                    min=j;
            }
            swarp(i,min);
        }
    }

    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new SelectSorter(data).test();
        }

    }
}
