package cn.ziran.suanfa;

import org.apache.commons.lang.StringUtils;

public class XMaopao {
  
  public static void main(String[] args) {
    int[] arr=new int[10];
    arr[0]=1;
    arr[1]=4;
    arr[2]=3;
    arr[3]=5;
    arr[4]=6;
    arr[5]=2;
    arr[6]=0;
    arr[7]=-1;
    arr[8]=7;
    arr[9]=9;
    
    bubbleSort(arr);
    
  }

  public static void bubbleSort(int[] arr) {
    if (arr == null || arr.length == 0)
      return;
    int len = arr.length;
    for (int i = 0; i < len; i++) {
      for (int j = len - 1; j > i; j--) {
        if (arr[j] < arr[j - 1]) {
          swap(arr, j - 1, j);
        }
      }
    }
    System.out.println(1);
  }


  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
