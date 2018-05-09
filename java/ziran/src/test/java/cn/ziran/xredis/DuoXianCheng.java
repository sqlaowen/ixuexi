package cn.ziran.xredis;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DuoXianCheng {


  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    //executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
    executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    //executor=(ThreadPoolExecutor) Executors.newSingleThreadExecutor();
    for (int i = 0; i < 15; i++) {
      final int j=i;
      executor.execute(new Runnable() {
        
        @Override
        public void run() {
          System.out.println("正在执行task "+j+" "+Thread.currentThread().getName());
          try {
            //Thread.currentThread().join();
              Thread.currentThread().sleep(200);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("task "+j+"执行完毕"+" "+Thread.currentThread().getName());
          
        }
      });
      System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目："
          + executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
    }
    executor.shutdown();
    System.out.println("---------------------------------------");
  }
}
