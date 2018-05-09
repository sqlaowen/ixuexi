package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "dark roast coffee";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
