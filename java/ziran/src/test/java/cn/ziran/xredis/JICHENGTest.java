package cn.ziran.xredis;

public class JICHENGTest {
  public static void main(String[] args) {
    A ab = new B();
    ab = new B();
    
    Integer i=1;
    int i2=Integer.parseInt(i.toString());
  }
}


class A {

  static {
    System.out.print("1");
  }

  public A() {
    System.out.print("2");
  }
}


class B extends A {

  static {
    System.out.print("a");
  }

  public B() {
    System.out.print("b");
  }
}
