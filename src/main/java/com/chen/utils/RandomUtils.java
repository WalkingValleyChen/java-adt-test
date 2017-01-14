package com.chen.utils;

import java.util.Random;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/1/14
 */
public class RandomUtils {
    private static Random random=new Random();

    public static int[] generateRandomIntArray(int size,int max){

        int[] a=new int[size];
        for (int i = 0; i < a.length; i++) {
            a[i]=random.nextInt(max);
        }
        return a;
    }

    public static Integer[] generateRandomIntegerArray(int size,int max){

        Integer[] a=new Integer[size];
        for (int i = 0; i < a.length; i++) {
            a[i]=random.nextInt(max);
        }
        return a;
    }

    public static int nexInt(int max){
        return random.nextInt(max);
    }
}
