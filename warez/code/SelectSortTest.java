package com.jd;

public class SelectSortTest {
    public static void main(String[] args) {
        int[] intArr = {1, 0, 10, 9, 7, -1, 20};

        int minIndex = 0;
        for (int i = 0; i < intArr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < intArr.length; j++) {
                if (intArr[j] < intArr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = intArr[i];
                intArr[i] = intArr[minIndex];
                intArr[minIndex] = temp;
            } else {
                System.out.println("--------");
            }
        }

//        int min = 0;
//        int min_index = 0;
//        for (int i = 0; i < intArr.length - 1; i++) {
//            min = intArr[i];
//            min_index = i;
//            for (int j = i + 1; j < intArr.length; j++) {
//                if (intArr[j] < min) {
//                    min = intArr[j];
//                    min_index = j;
//                }
//            }
//            if (min_index != i) {
//                intArr[min_index] = intArr[i];
//                intArr[i] = min;
//            }
//        }
        for (int i : intArr) {
            System.out.println(i);
        }
    }
}
