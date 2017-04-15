package com.chen.sort;

import com.chen.utils.RandomUtils;

/**
 * 归并排序
 *
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class MergeSorter extends RecursiveMergeSorter {

    private Comparable[] copyData;

    public MergeSorter(Comparable[] data) {
        super(data);
        copyData = new Comparable[data.length];
    }

    @Override
    public void sort() {
        int length = 1;
        while (length <= data.length >> 1){
            for (int i = 0; i < data.length; i += length * 2) {
                int right = i + 2 * length-1;
                merge(i, i+right>>1, right<data.length?right:(data.length-1));
            }
            length*=2;
        }
    }


    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new MergeSorter(data).test();
        }


    }
}
