package cn.ziran.xredis;

public class NULLClass {

  public static void haha() {
    System.out.println("haha");
  }

  public static void main(String[] args) {
    Integer a = new Integer(3);
    Integer b = 3;                  // 将3自动装箱成Integer类型
    int c = 3;
    Integer d = 3;
    System.out.println(a == b);     // false 两个引用没有引用同一对象
    System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
    System.out.println(b == d); 
    System.out.println(a.equals(b)); 
    System.out.println(a.equals(d)); 
    
//    String s;
//  System.out.println("s=" + s);
    
    System.out.println("5" + 2);
  
    NULLClass.haha();
    ((NULLClass) null).haha();
    
    System.out.println("-------main start-------");
    new HelloB();
    new HelloB();
    System.out.println("-------main end-------");
    
    new Child("mike");
    
    new Dervied();   
  }


}


class HelloA {
  protected static int i;
  protected int j;
  public HelloA() {
    System.out.println(i);
    System.out.println(j);
    System.out.println("HelloA");
  }

  {
    System.out.println("I'm A class");
  }

  static {
    System.out.println("static A");
  }

}


class HelloB extends HelloA {
  protected static String x;
  protected String y;
  public HelloB() {
    System.out.println(i);
    System.out.println(j);
    System.out.println("HelloB");
  }

  {
    System.out.println("I'm B class");
  }

  static {
    System.out.println("static B");
  }

  // public static void main(String[] args) {
  // new HelloB();
  // }

}

class People {
  String name;

  public People() {
      System.out.print(1);
  }

  public People(String name) {
      System.out.print(2);
      this.name = name;
  }
}

class Child extends People {
  People father;

  public Child(String name) {
      System.out.print(3);
      this.name = name;
      father = new People(name + ":F");
  }

  public Child() {
      System.out.print(4);
  }
  
}

class Dervied extends Base {

  private String name = "dervied";

  public Dervied() {
      tellName();
      printName();
  }
  
  public void tellName() {
      System.out.println("Dervied tell name: " + name);
  }
  
  public void printName() {
      System.out.println("Dervied print name: " + name);
  }

//  public static void main(String[] args){
//      
//      new Dervied();    
//  }
}

class Base {
  
  private String name = "base";

  public Base() {
      tellName();
      printName();
  }
  
  public void tellName() {
      System.out.println("Base tell name: " + name);
  }
  
  public void printName() {
      System.out.println("Base print name: " + name);
  }
}