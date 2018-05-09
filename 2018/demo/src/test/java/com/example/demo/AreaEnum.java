package com.example.demo;

/**
 * @Description: ${todo}
 * @author: wenshiwei
 * @date: 2018-03-26
 */
public enum AreaEnum {
    HENAN(1, "河南"),
    BEIJING(2, "北京"),
    SHANGHAI(3, "上海"),
    SHANGQIU(4, "商丘");

    private Integer value;
    private String desc;

    private AreaEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AreaEnum getEnum(Integer areaVal) {
        for (AreaEnum areaEnum : AreaEnum.values()) {
            if (areaVal == areaEnum.getValue()) {
                return areaEnum;
            }
        }
        return null;
    }

    //region

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    //endregion
}
