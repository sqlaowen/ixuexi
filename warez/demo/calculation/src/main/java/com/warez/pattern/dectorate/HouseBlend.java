package com.warez.pattern.dectorate;

/**
 * Created by wenshiwei on 2017/1/19.
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }

}
