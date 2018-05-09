package cn.ziran.xdanli;

public class Singleton {

  private Singleton() {}

  private static Singleton singleton;

  //锁住整个对象, 性能下降
  public static synchronized Singleton getInstance() {
    if (null == singleton) {
      return new Singleton();
    }
    return singleton;
  }
  
}
