package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class Millk extends CondimentDecorator {

    Beverage beverage;

    public Millk(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + "\r\n Millk";
    }

    public double cost() {
        return 0.10 + beverage.cost();
    }

}
