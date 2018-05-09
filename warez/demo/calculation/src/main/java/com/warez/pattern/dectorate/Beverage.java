package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
