package com.warez;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by iyou on 2017/1/14.
 */
public class App {

    public static void main(String[] args) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(id);
        int hc=id.hashCode();
        System.out.println(hc);
        System.out.println(Math.abs(hc));

        List<String> list =new ArrayList<>();
        list.add("wen");
        list.add("shi");
        System.out.println(list);
    }
}
