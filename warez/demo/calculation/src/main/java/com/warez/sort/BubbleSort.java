package com.warez.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 冒泡排序
 * Created by wenshiwei on 2016/12/14.
 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] intArr = new Integer[10];
        Arrays.asList(2, 1, 4, 3, 9, 0, 7, 6, 5, 8).toArray(intArr);
        System.out.println("未排序:" + JSON.toJSONString(intArr));

        for (int i = intArr.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                // 比较 O(n*n)
                if (intArr[j] > intArr[j + 1]) {

                    // 交换 O(n*n)
                    int tmp = intArr[j];
                    intArr[j] = intArr[j + 1];
                    intArr[j + 1] = tmp;
                }
            }
        }

        System.out.println("排序后:" + JSON.toJSONString(intArr));
    }

}
