package com.zgxcw.wx.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by huolh on 2016/3/9.
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

  private static Map<String, String> ctxPropertiesMap;

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
    super.processProperties(beanFactoryToProcess, props);
    ctxPropertiesMap = new HashMap<String, String>();
    for (Object key : props.keySet()) {
      String keyStr = key.toString();
      String value = props.getProperty(keyStr);
      ctxPropertiesMap.put(keyStr, value);
    }
  }

  /**获取上下文的属性
   * @param name
   * @return
   */
  public static String getContextProperty(String name) {
    return ctxPropertiesMap.get(name);
  }

}

