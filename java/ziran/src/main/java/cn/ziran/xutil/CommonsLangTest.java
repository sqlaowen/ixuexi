package cn.ziran.xutil;

import java.lang.reflect.Field;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.reflect.FieldUtils;

import cn.ziran.xreflect.Son;

public class CommonsLangTest {

  public static void main(String[] args) {
    System.out.println(BooleanUtils.negate(true));
    System.out.println(NumberUtils.toInt("2"));
    Field field= FieldUtils.getField(Son.class, "sonDate");
    System.out.println(field);
  }
}
