package com.warez;

/**
 * Created by iyou on 2017/2/25.
 */
public class Test2 {

    public static void main(String[] args) {
        String str="我的老师把我干得水直流\n" +
                "\n" +
                "<strong><font color=\"#ff0000\">专注高端成人用品,好口碑,正品授权,专卖日本欧美进口成人性用品,夫妻情趣用品，迷情香水，进口印度神油:全国七大仓库,覆盖全国上千大小城市,私密发货,支持货到付款!&nbsp;&nbsp;</font><font color=\"#0000ff\">客服微信：LX549999，做回真男人。</font></strong>";
        str=str.replaceAll("<strong>.*<\\/strong>","");
        System.out.println(str);
    }
}
