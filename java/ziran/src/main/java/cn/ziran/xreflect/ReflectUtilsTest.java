//package cn.ziran.xreflect;
//
//public class ReflectUtilsTest {
//
//}
package cn.ziran.xreflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public class ReflectUtilsTest {

  /**
   * 测试获取父类的各个方法对象
   */
  
  @Test
  public void testGetDeclaredMethod() {
    
    Object obj = new Son() ;
    
    //获取公共方法名
    Method publicMethod = ReflectUtils.getDeclaredMethod(obj, "publicMethod") ;
    System.out.println(publicMethod.getName());
    
    //获取默认方法名
    Method defaultMethod = ReflectUtils.getDeclaredMethod(obj, "defaultMethod") ;
    System.out.println(defaultMethod.getName());
    
    //获取被保护方法名
    Method protectedMethod = ReflectUtils.getDeclaredMethod(obj, "protectedMethod") ;
    System.out.println(protectedMethod.getName());
    
    //获取私有方法名
    Method privateMethod = ReflectUtils.getDeclaredMethod(obj, "privateMethod") ;
    System.out.println(privateMethod.getName());
  }

  /**
   * 测试调用父类的方法
   * @throws Exception
   */
  
  @Test
  public void testInvokeMethod() throws Exception {
    Object obj = new Son() ;
    
    //调用父类的公共方法
    ReflectUtils.invokeMethod(obj, "publicMethod", null , null) ;
    
    //调用父类的默认方法
    ReflectUtils.invokeMethod(obj, "defaultMethod", null , null) ;
    
    //调用父类的被保护方法
    ReflectUtils.invokeMethod(obj, "protectedMethod", null , null) ;
    
    //调用父类的私有方法
    ReflectUtils.invokeMethod(obj, "privateMethod", null , null) ;
  }

  /**
   * 测试获取父类的各个属性名
   */
  
  @Test
  public void testGetDeclaredField() {
    
    Object obj = new Son() ;
    
    //获取公共属性名
    Field publicField = ReflectUtils.getDeclaredField(obj, "publicField") ;
    System.out.println(publicField.getName());
    
    
    //获取公共属性名
    Field defaultField = ReflectUtils.getDeclaredField(obj, "defaultField") ;
    System.out.println(defaultField.getName());
    
    //获取公共属性名
    Field protectedField = ReflectUtils.getDeclaredField(obj, "protectedField") ;
    System.out.println(protectedField.getName());
    
    //获取公共属性名
    Field privateField = ReflectUtils.getDeclaredField(obj, "privateField") ;
    System.out.println(privateField.getName());
    
  }

  @Test
  public void testSetFieldValue() {
    
    Object obj = new Son() ;
    
    System.out.println("原来的各个属性的值: ");
    System.out.println("publicField = " + ReflectUtils.getFieldValue(obj, "publicField"));
    System.out.println("defaultField = " + ReflectUtils.getFieldValue(obj, "defaultField"));
    System.out.println("protectedField = " + ReflectUtils.getFieldValue(obj, "protectedField"));
    System.out.println("privateField = " + ReflectUtils.getFieldValue(obj, "privateField"));
    
    ReflectUtils.setFieldValue(obj, "publicField", "a") ;
    ReflectUtils.setFieldValue(obj, "defaultField", "b") ;
    ReflectUtils.setFieldValue(obj, "protectedField", "c") ;
    ReflectUtils.setFieldValue(obj, "privateField", "d") ;
    
    System.out.println("***********************************************************");
    
    System.out.println("将属性值改变后的各个属性值: ");
    System.out.println("publicField = " + ReflectUtils.getFieldValue(obj, "publicField"));
    System.out.println("defaultField = " + ReflectUtils.getFieldValue(obj, "defaultField"));
    System.out.println("protectedField = " + ReflectUtils.getFieldValue(obj, "protectedField"));
    System.out.println("privateField = " + ReflectUtils.getFieldValue(obj, "privateField"));
    
  }

  @Test
  public void testGetFieldValue() {
    
    Object obj = new Son() ;
    
    System.out.println("publicField = " + ReflectUtils.getFieldValue(obj, "publicField"));
    System.out.println("defaultField = " + ReflectUtils.getFieldValue(obj, "defaultField"));
    System.out.println("protectedField = " + ReflectUtils.getFieldValue(obj, "protectedField"));
    System.out.println("privateField = " + ReflectUtils.getFieldValue(obj, "privateField"));
  }

  
  @Test
  public void test10(){
    Collection<Field> list=  ReflectUtils.getAllDeclaredFields(new Sunzi());
    System.out.println(list);
  }
  
}

