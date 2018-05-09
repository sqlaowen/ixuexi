package cn.ziran.fanshe;

import java.util.Hashtable;

public class XTest {

  public static void main(String[] args) throws Exception {
    // 不会初始化静态块
    Class<?> clazz1 = XBase.class;
    System.out.println("------");
    // 会初始化
    Class<?> clazz2 = Class.forName("cn.ziran.fanshe.XBase");
    
    
    Hashtable<Object, Object> h=new XTest().new MHashtable();
    
    h.put("1", "1");
    h.put("11", "1");
    h.put("12", "12");
    h.put("13", "13");
    h.put("-", "-");
    h.put("s", "s");
    h.put("d", "d");
    h.put("3", "3");
    h.put("4", "4");
    h.put("5", "5");
    h.put("121", "121");
    h.put("t", "t");
    h.put("x", "x");
    h.put("v", "v");
    h.put("b", "b");
    System.out.println(h);
    
    System.out.println(h.get("x"));
    System.out.println(h.get("12"));
    
  }
  
  public class MHashtable extends Hashtable<Object, Object>{
    private static final long serialVersionUID = 1L;

    @Override
    public int hashCode() {
      return 0;
    }
  }

  
}
