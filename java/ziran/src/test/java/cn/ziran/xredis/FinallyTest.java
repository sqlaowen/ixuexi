package cn.ziran.xredis;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class FinallyTest {

  public static void main(String[] args) throws Exception {
    // String msg = showMsg();
    // System.out.println(msg);
    // String[] msg2 = showMsg2();
    // System.out.println(StringUtils.join(msg2, ","));
    //
    // Person p = showMsg3();
    // System.out.println(JSON.toJSONString(p));

    try {
      try {
        throw new FinallyTest().new Sneeze();
      } catch (Annoyance a) {
        System.out.println("Caught Annoyance");
        throw a;
      }
    } catch (Sneeze s) {
      System.out.println("Caught Sneeze");
      return;
    } finally {
      System.out.println("Hello World!");
    }
  }

  class Annoyance extends Exception {

    private static final long serialVersionUID = 1L;
  }
  class Sneeze extends Annoyance {
    private static final long serialVersionUID = 1L;
  }


  static String showMsg() {
    String msg = "1";
    try {
      return msg;
    } finally {
      msg = "finally";
      System.out.println("finally ...");
    }
  }

  static String[] showMsg2() {
    String[] msg = new String[] {"1", "2"};
    try {
      return msg;
    } finally {
      // msg = new String[] {"3", "4"};
      msg[0] = "3";
      msg[1] = "4";
      System.out.println("finally ...");
    }
  }

  static Person showMsg3() {
    Person p = new Person();
    p.setId("id1");
    p.setAge(1);
    p.setName("1");
    try {
      return p;
    } finally {
      // p = new Person();
      p.setId("id2");
      p.setAge(2);
      p.setName("2");
      System.out.println("finally ...");
    }
  }
}
