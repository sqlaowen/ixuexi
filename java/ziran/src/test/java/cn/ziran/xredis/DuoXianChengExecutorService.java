package cn.ziran.xredis;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DuoXianChengExecutorService {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(20);
    Long l1 = System.currentTimeMillis();
    for (int i = 0; i < 100; i++) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out
              .println(Thread.currentThread().getName() + " --> " + System.currentTimeMillis());
        }
      });
    }
    executor.shutdown();
    try {
      // 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行
      // 设置最长等待10秒
      executor.awaitTermination(1, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Long l2 = System.currentTimeMillis();
    System.out.println("消费时间: " + (l2 - l1));



//    // 使用线程安全的Vector
//    Vector<Thread> threads = new Vector<Thread>();
//    Thread iThread=null;
//    for (int i = 0; i < 10; i++) {
//      iThread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//          try {
//            Thread.sleep(1000);
//            // 模拟子线程任务
//          } catch (InterruptedException e) {
//          }
//          System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
//
//        }
//      },"thread"+i);
//
//      threads.add(iThread);
//      iThread.start();      
//    }
//
//    for (Thread thread : threads) {
//      try {
//        // 等待所有线程执行完毕
//          thread.join();          
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//    System.out.println("主线执行。");
  }
}
