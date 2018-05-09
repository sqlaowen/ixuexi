package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by iyou on 2017/4/16.
 */
@Component
public class PropertyUtil {

    private static Map propsMap;

    /**
     * 通过key查询配置
     *
     * @param key
     * @return
     */
    public static String getPropertyByKey(String key) {
        if (null != propsMap) {
            for (Object str : propsMap.keySet()) {
                if (key.equals(str)) {
                    return propsMap.get(key).toString();
                }
            }
        }
        return null;
    }

    public Map getPropsMap() {
        return propsMap;
    }

    @Autowired
    public void setPropsMap(Map propsMap) {
        PropertyUtil.propsMap = propsMap;
    }

}
