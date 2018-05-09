package cn.ziran.xreflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

  // 代理类持有一个委托类的对象引用
  private Object target;
  public MyInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//    System.out.println(proxy);
    System.out.println(method);
    for (Object object : args) {
      System.out.println(object);
    }
   Object obj= method.invoke(target, args);
   return obj;
  }

}
