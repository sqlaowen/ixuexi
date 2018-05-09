package cn.ziran.xredis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DuoXianCheng2 {

  public static void main(String[] args) {

//     xx();
//     System.out.println("---------------------------------------");
//     
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//        new LinkedBlockingQueue<Runnable>());
//
//    final DuoXianCheng2 d = new DuoXianCheng2();
//    for (int i = 0; i < 20; i++) {
//      final int x = i;
//      executor.execute(new Runnable() {
//        @Override
//        public  void run() {
//          d.yy(x);
//        }
//      });
//    }
//    System.out.println("---------------------------------------");
    
    List<Runnable> runnableList=new ArrayList<>();
    
    Thread t1=  new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("thread1 执行完毕");
      }
    });
    
    Thread t2=  new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("thread2 执行完毕");
      }
    });
    
    Thread t3=  new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("thread3 执行完毕");
      }
    });
    runnableList.add(t1);
    runnableList.add(t2);
    runnableList.add(t3);
    
    for(int i=0;i<10;i++){
    for(Runnable runnable: runnableList) {
//      runnable.run();
      synchronized (DuoXianCheng2.class ) {
        ((Thread)runnable).start();
      }
    }
    System.out.println("==============================");
    }
  }

  private static Runnable synchronization(Class<DuoXianCheng2> class1) {
    // TODO Auto-generated method stub
    return null;
  }

  public void yy(int i) {
    System.out.println(i);
  }

  public static void xx() {
    System.out.println("---------------------------------");
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("thread1 执行完毕");
      }
    }).start();

    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("thread2 执行完毕");
      }
    }).start();

    System.out.println("主线程执行完毕...");
  }
}
