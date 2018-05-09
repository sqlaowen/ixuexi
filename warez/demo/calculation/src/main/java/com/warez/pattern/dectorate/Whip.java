package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + "\r\n Whip";
    }

    public double cost() {
        return 0.10 + beverage.cost();
    }

}
