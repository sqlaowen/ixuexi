package com.warez.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 选择排序
 * Created by wenshiwei on 2016/12/14.
 */
public class SelectSort {

    public static void main(String[] args) {
        Integer[] intArr = new Integer[10];
        Arrays.asList(2, 1, 4, 3, 9, 0, 7, 6, 5, 8).toArray(intArr);
        System.out.println("未排序:" + JSON.toJSONString(intArr));

        for (int i = 0; i < intArr.length - 1; i++) {
            // 认为外循环第一个为最小值
            int min = intArr[i];
            int minIndex = i;

            // 循环比较找到真正的最小值 O(n*n)
            for (int j = i + 1; j < intArr.length; j++) {
                if (min > intArr[j]) {
                    min = intArr[j];
                    minIndex = j;
                }
            }
            // 交换 O(n)
            int tmp = intArr[i];
            intArr[i] = intArr[minIndex];
            intArr[minIndex] = tmp;
        }

        System.out.println("排序后:" + JSON.toJSONString(intArr));
    }

}
