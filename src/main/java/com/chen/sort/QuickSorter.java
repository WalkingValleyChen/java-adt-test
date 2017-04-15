package com.chen.sort;

import com.chen.utils.RandomUtils;

/**
 * 快速排序
 *
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class QuickSorter extends Sorter{

    public QuickSorter(Comparable[] data) {
        super(data);
    }

    @Override
    public void sort() {
        partitionSort(0,data.length-1);
    }

    public void partitionSort(int lo,int hi){

        if(lo>=hi) return;

        int mid = partition(lo, hi);
        partitionSort(lo,mid-1);
        partitionSort(mid+1,hi);
    }

    public int partition(int lo,int hi){
        int check = lo+hi>>1;
        swarp(lo,check);
        Comparable checkData = data[lo];
        int i=lo,j=hi+1;


        while (true){
            while (lt(data[++i],checkData))if(i==hi) break;
            while (gt(data[--j],checkData)) if(j==lo)break;
            if(i>=j) break;
            swarp(i,j);
        }
        swarp(lo,j);
        return j;
    }

    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new QuickSorter(data).test();
        }
    }
}
