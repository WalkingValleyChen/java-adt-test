package com.chen.sort;

import com.chen.utils.RandomUtils;

/**
 * 希尔排序
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class HillSorter extends Sorter {
    public HillSorter(Comparable[] data) {
        super(data);
    }

    @Override
    public void sort() {
        int N = data.length;
        int step=1;
        while (step<N/3){
            step=step*3+1;
        }

        while (step>=1){
            for (int i = step; i < data.length; i++) {
                for (int j = i; j >=step && lt(data[j],data[j-step]) ; j-=step) {
                    swarp(j,j-step);
                }
            }
            step=step/3;
        }
    }

    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new HillSorter(data).test();
        }

    }
}
