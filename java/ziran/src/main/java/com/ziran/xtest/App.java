package com.ziran.xtest;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) throws Exception {
    Class<?> c = Class.forName("com.ziran.xtest.Cat");
    ICat cat = (ICat) c.newInstance();
    cat.cry("小猫");
  }
}
