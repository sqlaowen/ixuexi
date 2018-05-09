package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + "\r\n Mocha";
    }

    public double cost() {
        return 0.20 + beverage.cost();
    }

}
