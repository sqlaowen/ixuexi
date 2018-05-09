package cn.ziran.xutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取包外的资源
 *
 */
public class PropUtil {
  
//  public static void main(String[] args) throws IOException {
//    String sp=File.separator;
//    String filePath=System.getProperty("user.dir")+sp+"conf"+sp+"log4j.properties";
//    System.out.println(GetPropValByKey(filePath, "log4j.rootLogger"));
//  }

  /**
   * 
   * @param filePath
   * @param key
   * @return
   * @throws IOException
   */
  public static String GetPropValByKey(String filePath, String key) throws IOException {
    Properties pro = new Properties();
    InputStream in = new FileInputStream(filePath);
    pro.load(in);
    if(in!=null)
      in.close();
    return pro.getProperty(key);
  }

  /**
   * 
   * @param filePath
   * @return
   * @throws IOException
   */
  public static Map<String, String> GetAllProp(String filePath) throws IOException {
    Properties pro = new Properties();
    InputStream in = new FileInputStream(filePath);
    pro.load(in);
    if(in!=null)
      in.close();
    Map<String, String> map = new HashMap<String, String>();
    Enumeration<?> eu = pro.propertyNames();
    while (eu.hasMoreElements()) {
      String strKey = (String) eu.nextElement();
      String strValue = pro.getProperty(strKey);
      map.put(strKey, strValue);
    }
    return map;
  }


  /**
   * 
   * @param filePath
   * @param key
   * @param value
   * @param add [true:add , false:edit]
   * @throws IOException
   */
  public static void EditProp(String filePath, String key, String value,boolean add) throws IOException {
    Properties pro = new Properties();
    InputStream in = new FileInputStream(filePath);
    pro.load(in);
    if(in!=null)
      in.close();
    if(add)
      pro.put(key, value);
    else
      pro.setProperty(key, value);
    OutputStream out = new FileOutputStream(filePath);
    pro.store(out, "edit conf...");
    if(out!=null)
      out.close();
  }
}
