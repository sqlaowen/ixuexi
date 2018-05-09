package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "decaf coffee";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
