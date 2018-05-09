package com.ziran.xtest;

public class Cat implements ICat {

  @Override
  public String cry(String a) {
    return ">>>>> " + a + " miao miao ...";
  }

  public static void main(String[] args) {
    System.out.println(ICat.class.getName());
  }
}
