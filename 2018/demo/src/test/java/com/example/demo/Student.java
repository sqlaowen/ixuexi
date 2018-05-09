package com.example.demo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: ${todo}
 * @author: wenshiwei
 * @date: 2018-03-29
 */
@Data
public class Student {
    private Integer id;
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private String idName;

    public String getIdName() {
        if (null == id) {
            return "";
        }
        return "name";
    }
}
