package com.example.demo;

/**
 * @Description: ${todo}
 * @author: wenshiwei
 * @date: 2018-03-26
 */
public class EnumTest {
    public static void main(String[] args) {
        Integer areaVal = 1;
        AreaEnum anEnum = AreaEnum.getEnum(areaVal);
        System.out.println(anEnum.getDesc());
    }
}
