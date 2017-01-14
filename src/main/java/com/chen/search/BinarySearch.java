package com.chen.search;

import com.chen.utils.RandomUtils;

import java.util.Arrays;

/**
 * 二分查找
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class BinarySearch {

    /**
     * int数组二分查找，未找到返回-1
     * @param a
     * @param find
     * @return
     */
    public static int search(int[] a, int find) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi)>>1;
            if (a[mid] > find)
                hi = mid - 1;
            else if (a[mid] < find)
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }


    public static void main(String[] args) {
        for (int i = 10; i < 10000000; i *= 2) {
            int[] data = RandomUtils.generateRandomIntArray(i, i);
            Arrays.sort(data);

            int target = RandomUtils.nexInt(i);
            int index = search(data, target);
            if (index != -1) {
                assert target == data[index];
                System.out.println(String.format("array size %d find %d index is %d array[%d] is %d", i, target, index, index, data[index]));
            }
        }
    }
}
