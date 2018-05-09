package cn.ziran.xredis;

import com.alibaba.fastjson.JSON;
import com.github.jedis.lock.JedisLock;

///redis分部式锁 使用方式
public class RedisLockUser {

//  /**
//   * 从蛋队列中取出一个队列蛋
//   */
//  public QueueEgg getOneQueueEggFromEggQ() {
//    log.debug("---- QueueEggManager getOneQueueEggFromEggQ start");
//    QueueEgg queueEgg = null;
//    // 加锁 -- 开始
//    JedisLock locker = jedisLockManager.getJedisLock(SmashEggConstant.LOCK_KEY_QUEUE_EGG, 1000 * 5, 1000 * 10);
//    try {
//      jedisLockManager.lock(locker, (int)(Math.random() * 200));// 加锁
//      
//      // 如果队列长度等于蛋队列极限长度 另起一个线程填充蛋队列
//      Long eggQLength = redisService.length(SmashEggConstant.QUEUE_KEY_QUEUE_EGG);
//      if (eggQLength == limitSize) {
//        new Thread(new FillEggQueueThread()).start();
//        Thread.sleep(100);
//      }
//      // 从队列中取出一个队列蛋
//      String queueEggJsonStr = redisService.outQueue(SmashEggConstant.QUEUE_KEY_QUEUE_EGG);
//      queueEgg = JSON.parseObject(queueEggJsonStr, QueueEgg.class);
//    } catch (InterruptedException e) {
//      log.error("---- 获取锁异常 ...", e);
//    } finally {
//      // 释放锁
//      locker.release();
//    }
//    log.debug("---- QueueEggManager getOneQueueEggFromEggQ end");
//    return queueEgg;
//  }
}
