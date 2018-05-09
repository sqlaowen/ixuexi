package cn.ziran.test;

import cn.ziran.test.LambdaTest.X3;

/**
 * Created by wenshiwei on 2016/11/24.
 */
public class X2 {

    public static void main(String[] args) {
        X1 x1 = new X1() {
            @Override
            protected String xx() {
                return "wenshiwei";
            }

            //@Override
            public void fn1() {
                System.out.println("fn1");
            }
        };
        x1.fn1();

        X1 x12=new X1();
        if(x12 instanceof X1){
            System.out.println("判断实例...");
        }
        X3 x3=new X3();
        if(x3 instanceof X1){
            System.out.println("子类可以做为父类的实例...");
        }
    }
}
