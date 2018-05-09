package com.jd;

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] intArr = {1, 0, 10, 9, 7, -1, 20};

        for (int i = intArr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (intArr[j] > intArr[j + 1]) {
                    int temp = intArr[j];
                    intArr[j] = intArr[j + 1];
                    intArr[j + 1] = temp;
                }
            }
        }

        for (int i : intArr) {
            System.out.println(i);
        }
    }

}
