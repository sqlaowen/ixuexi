package com.jd;

public class InsertSortTest {

    public static void main(String[] args) {
        int[] intArr = {1, 0, 10, 9, 7, -1, 20};
        for (int i = 1; i < intArr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (intArr[j] < intArr[j - 1]) {
                    int temp = intArr[j - 1];
                    intArr[j - 1] = intArr[j];
                    intArr[j] = temp;
                }
            }
        }

        for (int i : intArr) {
            System.out.println(i);
        }

    }
}
