package com.warez.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 插入排序
 * Created by wenshiwei on 2016/12/14.
 */
public class InsertSort {

    public static void main(String[] args) {
//        System.out.println("d".compareTo("a")); //>0
//        System.out.println("d".compareTo("d")); //=0
//        System.out.println("d".compareTo("f")); //<0

        Integer[] intArr = new Integer[10];
        Arrays.asList(2, 1, 4, 3, 9, 0, 7, 6, 5, 8).toArray(intArr);
        System.out.println("未排序:" + JSON.toJSONString(intArr));

        // 外循环从1开始, 1左边的认为是有序的, 右边无序
        for (int outer = 1; outer < intArr.length; outer++) {
            int inner = outer; // 内循环从outer开始
            int tmp = intArr[outer]; // 标记分割线(左边有线, 右边无序)
            while (0 < inner && tmp < intArr[inner - 1]) { //
                intArr[inner] = intArr[inner - 1]; //向右移动

                inner--;
            }
            intArr[inner] = tmp; // 插入
        }

        System.out.println("排序后:" + JSON.toJSONString(intArr));
    }

}
