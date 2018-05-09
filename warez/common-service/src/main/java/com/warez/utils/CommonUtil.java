package com.warez.utils;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * xml 转 Map<String, String>
     *
     * @param xml
     * @return
     */
    public static Map<String, String> getMapFromXML(String xml) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(xml)) {
            log.info("入参xml为空...");
            return map;
        }
        try {
            SAXReader saxReader = new SAXReader();
            InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document doc = saxReader.read(in);
            Element root = doc.getRootElement();
            for (Element e : (List<Element>) root.elements()) {
                map.put(e.getName(), e.getTextTrim());
            }
        } catch (Exception e) {
            log.error("Dom4j读取xml异常,对应xml:{},异常信息:{}", xml, e.getMessage());
        }
        return map;
    }

    /**
     * 递归读取xml节点和属性,返回map
     *
     * @param xml
     * @return
     */
    public static Map<String, String> getMapFromXMLWithAttr(String xml, List<String> attrlist) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(xml)) {
            log.info("入参xml为空...");
            return map;
        }
        try {
            SAXReader saxReader = new SAXReader();
            InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document doc = saxReader.read(in);
            Element root = doc.getRootElement();
            ele2map(map, root, attrlist);
        } catch (Exception e) {
            log.error("Dom4j读取xml异常,xml:{},异常信息:{}", xml, e.getMessage());
        }
        return map;
    }

    /**
     * Element转Map<String, String>
     *
     * @param map
     * @param ele
     * @return
     */
    private static void ele2map(Map<String, String> map, Element ele, List<String> attrList) {

        for (Element e : (List<Element>) ele.elements()) {
            if (null != attrList) {
                for (String str : attrList) {
                    if (StringUtils.isNotBlank(e.attributeValue(str))) {
                        map.put(str, e.attributeValue(str));
                    }
                }
            }
            if (e.elements().size() == 0) {
                map.put(e.getName(), e.getTextTrim());
            } else {
                ele2map(map, e, attrList);
            }
        }
    }

    /**
     * map转xml
     *
     * @param map
     * @return
     */
    public static String map2XML(Map<String, String> map) {
        if (null == map || map.size() == 0) {
            log.info("入参map为空...");
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Iterator<?> iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            sb.append(String.format("<%s><![CDATA[%s]]></%s>", entry.getKey(), entry.getValue(), entry.getKey()));
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * bean2map
     *
     * @param bean
     * @param isExcludeNullValue [true:排除空值, false:不排除空值]
     * @return
     */
    public static Map<String, String> bean2Map(Object bean, boolean isExcludeNullValue) {
        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result.toString());
                    } else {
                        if (!isExcludeNullValue) {
                            returnMap.put(propertyName, "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("bean2Map error:{}", e.getMessage());
        }
        return returnMap;
    }
}
