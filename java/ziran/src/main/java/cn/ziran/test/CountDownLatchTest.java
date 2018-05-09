package cn.ziran.test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wenshiwei on 2016/11/24.
 * <p>
 * http://blog.csdn.net/a19881029/article/details/36180365
 */
public class CountDownLatchTest extends Thread {

    private static final CountDownLatch startLatch = new CountDownLatch(1);
    private static final CountDownLatch stopLatch = new CountDownLatch(100);

    @Override
    public void run() {
        try {
            startLatch.await();//wait方法使当前线程在计数器倒计数至0之前一直等待，除非等待中的线程中断或等待超时
            System.out.println(Thread.currentThread().getName() + "do something!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopLatch.countDown();//countDown方法递减计数器计数，如果计数到达0，则释放所有等待中的线程
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new CountDownLatchTest()).start();
        }
        System.out.println(new Date().toString());
        startLatch.countDown();
        stopLatch.await();
        System.out.println(new Date().toString());

//        //以下为学习
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "do something!");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    countDownLatch.countDown();
//                }
//            }).start();
//        }
//        countDownLatch.await();
//        System.out.println("-----------------end");
    }
}
