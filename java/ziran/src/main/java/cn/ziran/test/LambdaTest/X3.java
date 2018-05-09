package cn.ziran.test.LambdaTest;

import cn.ziran.test.X1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by wenshiwei on 2016/11/29.
 */
public class X3 extends X1 {

    public static void main(String[] args) {

//        int [] irr={1,2,3,4,5};
//        int [] i2= Arrays.copyOf(irr, 6);
//        for (int i : i2) {
//            System.out.println(i);
//        }

        String x="xyz";
        x.concat("abc");
        System.out.println(x);

        List<String> lstr = Arrays.asList(new String[]{"a","b","c"});
        lstr.add("ab");// 报异常, asList转换的不能改变其大小

//        int[] arr1 = {1, 2, 3, 4, 5};
//        int[] arr2 = new int[5];
//
//        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
//        Arrays.stream(arr2).forEach(i-> System.out.println(i));

//        long start_01 = System.currentTimeMillis();
//        String a = "a";
//        for (int i = 0; i < 100000; i++) {
//            a += "b";
//            //等价于  a = new StringBuilder(a).append("b").toString();
//        }
//        long end_01 = System.currentTimeMillis();
//        System.out.println("  +   所消耗的时间：" + (end_01 - start_01) + "毫米");
//
//        //concat()
//        long start_02 = System.currentTimeMillis();
//        String c = "c";
//        for (int i = 0; i < 100000; i++) {
//            c = c.concat("d");
//            //
//        }
//        long end_02 = System.currentTimeMillis();
//        System.out.println("concat所消耗的时间：" + (end_02 - start_02) + "毫米");
//
//        //append
//        long start_03 = System.currentTimeMillis();
//        StringBuffer e = new StringBuffer("e");
//        for (int i = 0; i < 100000; i++) {
//            e.append("d");
//        }
//        long end_03 = System.currentTimeMillis();
//        System.out.println("append所消耗的时间：" + (end_03 - start_03) + "毫米");

    }
}
