package cn.ziran.xredis;

import com.github.jedis.lock.JedisLock;

import redis.clients.jedis.Jedis;


/**
 * redis分部式锁
 * JedisLock
 * 
 * @author jinlj
 *
 */
public class JedisLockManager {
  private String hostname;
  private int port;

  public JedisLockManager(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }
  // -------------------------------------------------------------------------------------------------------

  public JedisLock getJedisLock(String lockKey, int timeoutMsecs, int expireMsecs) {
    Jedis jedis = new Jedis(hostname, port);
    JedisLock locker = new JedisLock(jedis, lockKey, timeoutMsecs, expireMsecs);
    return locker;
  }

  public void lock(JedisLock locker, int waitMsecs) throws InterruptedException {
    while (!locker.acquire()) {
      Thread.sleep(waitMsecs);
    }
  }
}
