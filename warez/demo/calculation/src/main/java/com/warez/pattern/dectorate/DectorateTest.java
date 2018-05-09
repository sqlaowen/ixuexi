package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class DectorateTest {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()
                + "\r\n$" + beverage.cost());
        System.out.println("------------------------------");

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
                + "\r\n$" + beverage2.cost());
        System.out.println("------------------------------");

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
                + "\r\n$" + beverage3.cost());
        System.out.println("------------------------------");
    }

}
