package com.chen.utils;

import java.util.List;

/**
 * @author ValleyChen
 * @version 1.0.0
 * @time 2017/4/16
 */
public class ListUtils {

    public static String join(List list,String sp){
        if(list==null)
            return null;
        return list.stream().map(a->a.toString()).reduce((a,b)->a+sp+b).get().toString();
    }
}
