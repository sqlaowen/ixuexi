package cn.ziran.xredis;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class StrChuanDi {

  public static void main(String[] args) {
    String str = "123";
    changeStr(str);
    System.out.println(str);

    String[] msg = new String[] {"1", "2"};
    changeStr2(msg);
    System.out.println(StringUtils.join(msg, ","));
    
    Person p = new Person();
    p.setId("id1");
    p.setAge(1);
    p.setName("1");
    changeStr3(p);
    System.out.println(JSON.toJSONString(p));

  }

  static void changeStr(String str) {
    str = "welcome";
    System.out.println(str);
  }

  static void changeStr2(String[] msg) {
    msg = new String[] {"3", "4"};
  }

  static void changeStr3(Person p) {
    //p = new Person();
    p.setId("id2");
    p.setAge(2);
    p.setName("2");
  }
}
