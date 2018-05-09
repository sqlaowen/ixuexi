package cn.ziran.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenshiwei on 2016/11/30.
 */
public class TimerTest {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
                try {
                    Thread.sleep(4 * 1000); // 如果这里时间过长, 会导致 timerTwo 不能在计划时间内执行
                } catch (InterruptedException e) {
                    System.out.println("线程休眠异常...");
                }
                // throw new RuntimeException(); //如果这里抛出异常, 会导致 timerTwo 不能执行
            }
        }, 1 * 1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timerTwo invoked ,the time:" + (System.currentTimeMillis() - start));
            }
        }, 3 * 1000);

        /////////////////////////以上问题可以用 ScheduledExecutorService 解决///////////////////////////

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            System.out.println("scheduledExecutorService timer1 invoked .....");
            try {
                Thread.sleep(4 * 1000);
            } catch (InterruptedException e) {
                System.out.println("线程休眠异常...");
            }
            throw new RuntimeException();
        }, 1 * 1000, TimeUnit.MILLISECONDS);

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduledExecutorService timer2 invoked .....");
            }
        }, 1 * 1000, TimeUnit.MILLISECONDS);
    }

}
