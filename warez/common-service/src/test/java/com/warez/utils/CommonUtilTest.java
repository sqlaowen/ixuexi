package com.warez.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wenshiwei on 2016/10/12.
 */
public class CommonUtilTest {

    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        for (Iterator<?> iter = map.keySet().iterator(); iter.hasNext(); ) {
            String key = (String) iter.next();
            System.out.println(key);
        }
        String xml = CommonUtil.map2XML(map);
        System.out.println(xml);
    }
}
