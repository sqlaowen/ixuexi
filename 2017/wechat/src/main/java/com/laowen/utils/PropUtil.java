package com.laowen.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by iyou on 2017/4/8.
 */
public class PropUtil {

    private static Map<String, String> propsMap;

    public static String getProp(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        for (String _key : propsMap.keySet()) {
            if (_key.equals(key)) {
                return propsMap.get(key);
            }
        }
        return null;
    }

    public Map<String, String> getPropsMap() {
        return propsMap;
    }

    public void setPropsMap(Map<String, String> propsMap) {
        PropUtil.propsMap = propsMap;
    }
}
