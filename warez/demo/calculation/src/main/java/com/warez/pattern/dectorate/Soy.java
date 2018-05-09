package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + "\r\n Soy";
    }

    public double cost() {
        return 0.15 + beverage.cost();
    }

}
