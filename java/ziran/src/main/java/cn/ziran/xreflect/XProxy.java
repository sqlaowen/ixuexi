package cn.ziran.xreflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ziran.xtest.Cat;
import com.ziran.xtest.ICat;

public class XProxy {

  public static void main(String[] args) {
    Cat c0 = new Cat();
    ICat cat0 = (ICat) Proxy.newProxyInstance(c0.getClass().getClassLoader(),
        new Class<?>[] {ICat.class}, new MyInvocationHandler(c0));
    String x = cat0.cry("小狗");
    System.out.println(x);

    System.out.println("//========================================================");
    //========================================================
    
    ICat cat = (ICat) Proxy.newProxyInstance(ICat.class.getClassLoader(),
        new Class<?>[] {ICat.class}, new InvocationHandler() {
          Cat c = new Cat();
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method);
            for (Object object : args) {
              System.out.println(object);
            }
            Object o = method.invoke(c, args);
            return o;
          }
        });
    System.out.println(cat.cry("xxxx"));
  }
}
