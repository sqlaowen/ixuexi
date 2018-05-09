package cn.ziran.test;

/**
 * Created by wenshiwei on 2016/11/25.
 * <p>
 * http://blog.csdn.net/chenssy/article/details/12786385
 */
public class JichengTest {

    public static void main(String[] args) {
        A a1 = new JichengTest().new A();
        A a2 = new JichengTest().new B();
        B b = new JichengTest().new B();
        C c = new JichengTest().new C();
        D d = new JichengTest().new D();

        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));
        System.out.println("4--" + a2.show(b));
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));
        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));
    }

    public class A {
        public String show(D obj) {
            return ("A and D");
        }

        public String show(A obj) {
            return ("A and A");
        }

    }

    public class B extends A {
        public String show(B obj) {
            return ("B and B");
        }

        public String show(A obj) {
            return ("B and A");
        }
    }

    public class C extends B {

    }

    public class D extends B {

    }

}
