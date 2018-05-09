package cn.ziran.test;

/**
 * Created by wenshiwei on 2016/11/24.
 * <p>
 * http://blog.csdn.net/u013938165/article/details/22378615
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocalTest sn = new ThreadLocalTest();
        //3.3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    //4.每个线程打出3个序列值
                    System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
                }
            }
        }, "threadName");
        thread.start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                //4.每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
            }
        }).start();

    }

    // 每个线程所产生的序号虽然都共享同一个 ThreadLocalTest 实例，
    // 但它们并没有发生相互干扰的情况，而是各自产生独立的序列号，
    // 这是因为我们通过ThreadLocal为每一个线程提供了单独的副本
    ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() { //1.通过匿名内部类覆盖 ThreadLocal 的 initialValue() 方法，指定初始值
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    // 多个线程间持续累加
    private Integer integer;

    //2.获取下一个序列值
    public int getNextNum() {
        //System.out.println("当前值: " + seqNum.get());
        seqNum.set(seqNum.get() + 1);
        //System.out.println("新  值: " + seqNum.get());
        return seqNum.get();
    }

    public Integer getInteger() {
        if (null == integer) {
            integer = 0;
            return 0;
        }
        return ++integer;
    }

    private static class TestClient extends Thread {

        private ThreadLocalTest sn;

        public TestClient(ThreadLocalTest sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                //4.每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
                //4.每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] integer[" + sn.getInteger() + "]");
                System.out.println();
            }
        }
    }
}
