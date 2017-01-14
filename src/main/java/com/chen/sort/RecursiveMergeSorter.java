package com.chen.sort;

import com.chen.utils.RandomUtils;

import java.util.Arrays;

/**
 * 递归版本归并排序
 *
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class RecursiveMergeSorter extends Sorter {

    private Comparable[] copyData;

    public RecursiveMergeSorter(Comparable[] data) {
        super(data);
        copyData = new Comparable[data.length];
    }

    @Override
    public void sort() {
        mergeSort(0,data.length-1);
    }

    public void mergeSort(int left,int right){
        if(left>=right)
            return;
        int mid=left+right>>1;
        mergeSort(left,mid);
        mergeSort(mid+1,right);
        merge(left,mid,right);

    }

    public void merge(int left, int mid, int right) {
        for (int i = left; i <=right ; i++) {
            copyData[i]=data[i];
        }

        int i = left, j = mid + 1;
        for (int k = left; k <=right ; k++) {
            if(i>mid)
                data[k]=copyData[j++];
            else if(j>right)
                data[k]=copyData[i++];
            else if(lt(copyData[i],copyData[j]))
                data[k]=copyData[i++];
            else
                data[k]=copyData[j++];
        }
    }

    public static void main(String[] args) {
        for (int i = 5; i < 9; i++) {
            int size = 1 << i;
            Integer[] data = RandomUtils.generateRandomIntegerArray(size, size);
            new RecursiveMergeSorter(data).test();
        }

    }
}
