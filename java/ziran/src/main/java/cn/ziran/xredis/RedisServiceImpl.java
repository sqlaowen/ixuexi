package cn.ziran.xredis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @Title: RedisServiceImpL.java
 * @Description: redis 基础服务 实现类
 */
public class RedisServiceImpl implements RedisService {
  
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  // 读取redis
  private StringRedisTemplate stringRedisTemplateForRead;
  // 写入redis
  private StringRedisTemplate stringRedisTemplateForWrite;

  public void setStringRedisTemplateForRead(StringRedisTemplate stringRedisTemplateForRead) {
    this.stringRedisTemplateForRead = stringRedisTemplateForRead;
  }

  public void setStringRedisTemplateForWrite(StringRedisTemplate stringRedisTemplateForWrite) {
    this.stringRedisTemplateForWrite = stringRedisTemplateForWrite;
  }

  public void setData(String key, String value, int seconds) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForWrite.opsForValue();
    try {
      operations.set(key, value, seconds, TimeUnit.SECONDS);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入Redis 字符型数据失败！");
    }
  }

  public boolean resetTimeOut(String key, int seconds) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    if (seconds < 0) {
      throw new ServiceException("seconds不能为负数！");
    }
    try {
      return stringRedisTemplateForWrite.expire(key, seconds, TimeUnit.SECONDS);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("重置redis生存时间失败！KEY=【" + key + "】");
    }

  }

  public void setData(String key, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForWrite.opsForValue();
    try {
      operations.set(key, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入Redis 字符型数据失败！");
    }

  }

  public String getData(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForRead.opsForValue();
    String r;
    try {
      r = operations.get(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("读取字符型Redis失败！");
    }
    return r;
  }

  public Collection<String> getDatas(Collection<String> key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForRead.opsForValue();
    try {
      return operations.multiGet(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("批量读取字符型Redis失败！");
    }
  }

  public void del(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForWrite.opsForValue();
    try {
//      stringRedisTemplateForWrite.delete(key);
      operations.getOperations().delete(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("删除字符型Redis失败！");
    }
  }


  public void setHashData(String key, String hashKey, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForWrite.opsForHash();
    try {
      operations.put(key, hashKey, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入哈希型Redis数据失败！");
    }
  }

  public void setHashData(String key, String hashKey, String value, int seconds) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForWrite.opsForHash();
    try {
      operations.put(key, hashKey, value);
      stringRedisTemplateForWrite.expire(key, seconds, TimeUnit.SECONDS);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入哈希型Redis失败！");
    }
  }

  public String getHashData(String key, String hashKey) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForRead.opsForHash();
    String o;
    try {
      o = operations.get(key, hashKey);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("读取希型Redis失败！");
    }
    return o;

  }

  public Collection<String> getHashDatas(String key, Collection<String> hashKeys) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForRead.opsForHash();
    List<String> r;
    try {
      r = operations.multiGet(key, hashKeys);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("批量读取希型Redis数据失败！");
    }
    return r;
  }

  public List<String> getHashValues(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForRead.opsForHash();
    try {
      return operations.values(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("获取hash集合失败！");
    }
  }

  public Set<String> getHashKeys(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForRead.opsForHash();
    try {
      return operations.keys(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("获取hash key集合失败！");
    }
  }

  public void delHashData(String key, String hashKey) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForWrite.opsForHash();
    try {
      operations.delete(key, hashKey);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("删除希型Redis数据失败！");
    }
  }

  public boolean existsHashKey(String key, String hashKey) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForRead.opsForHash();
    try {
      return operations.hasKey(key, hashKey);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("判断哈希型Redis数据是否存在失败！");
    }
  }

  public Long setSetData(String key, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    SetOperations<String, String> operations = stringRedisTemplateForWrite.opsForSet();
    try {
      return operations.add(key, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入Set型 Redis数据失败！");
    }
  }

  public Long setSetData(String key, String value, int seconds) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    try {
      SetOperations<String, String> operations = stringRedisTemplateForWrite.opsForSet();
      Long r = operations.add(key, value);
      if (r > 0) {
        stringRedisTemplateForWrite.expire(key, seconds, TimeUnit.SECONDS);
      }
      return r;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("写入Set型 Redis数据失败！");
    }
  }

  public Set<String> getSetDatas(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    SetOperations<String, String> operations = stringRedisTemplateForRead.opsForSet();
    try {
      return operations.members(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("获取 Set型 Redis数据集合失败！");
    }
  }

  public boolean isSetMember(String key, String o) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(o)) {
      throw new ServiceException("key和value不能为null！");
    }
    SetOperations<String, String> operations = stringRedisTemplateForRead.opsForSet();
    try {
      return operations.isMember(key, o);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("判断 Set是否存在值 失败！");
    }
  }

  public Long delSet(String key, String o) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    SetOperations<String, String> operations = stringRedisTemplateForWrite.opsForSet();
    try {
      return operations.remove(key, o);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("删除 Set型 Redis数据 失败！");
    }
  }

  public Long increment(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForWrite.opsForValue();
    try {
      return operations.increment(key, 1);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("递增计数器写入失败！");
    }
  }

  public Long increment(String key, String hashKey) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForWrite.opsForHash();
    // ZSetOperations<String, String> ss = stringRedisTemplateForWrite.opsForZSet();
    try {
      return operations.increment(key, hashKey, 1);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("递增计数器写入失败！");
    }
  }

  public Long decrement(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ValueOperations<String, String> operations = stringRedisTemplateForWrite.opsForValue();
    try {
      return operations.increment(key, -1);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("递减计数器写入失败！");
    }
  }

  public Long decrement(String key, String hashKey) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    HashOperations<String, String, String> operations = stringRedisTemplateForWrite.opsForHash();
    try {
      return operations.increment(key, hashKey, -1);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("递减计数器写入失败！");
    }
  }

  public Long pushStack(String key, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.leftPush(key, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis压栈失败！");
    }
  }

  public String popStack(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.leftPop(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis出栈失败！");
    }
  }

  public Long inQueue(String key, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.rightPush(key, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis队列入队失败！");
    }
  }

  public String outQueue(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.leftPop(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis队列出队失败！");
    }
  }

  public Long length(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.size(key);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("查询Redis链表长度失败！");
    }
  }

  public List<String> range(String key, int start, int end) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.range(key, start, end);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("范围查询Redis链表失败！");
    }
  }

  public void remove(String key, long i, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      operations.remove(value, i, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("删除Redis链表失败！");
    }
  }

  public String index(String key, long index) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      return operations.index(key, index);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("读取Redis链表失败！");
    }
  }

  public void setLink(String key, long index, String value) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      operations.set(key, index, value);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis链表置值失败！");
    }
  }

  public void trim(String key, long start, int end) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    ListOperations<String, String> operations = stringRedisTemplateForWrite.opsForList();
    try {
      operations.trim(key, start, end);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("Redis链表剪裁失败！");
    }
  }

  public Map<String, List<String>> getHashKeysAndValues(String key) {
    if (key == null) {
      throw new ServiceException("key不能为null！");
    }
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    List<String> keyList = new ArrayList<String>();
    List<String> valueList = new ArrayList<String>();
    try {
      Set<String> keys = this.getHashKeys(key);
      Iterator<String> keyIter = keys.iterator();
      while (keyIter.hasNext()) {
        keyList.add(keyIter.next());
      }
      valueList = (List<String>) this.getHashDatas(key, keyList);
      map.put("keys", keyList);
      map.put("values", valueList);
      return map;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("批量查询 hashkeys和hashvalues失败");
    }
  }

  public boolean hasKey(String key) {
    return stringRedisTemplateForRead.hasKey(key);
  }

  public Long getExpire(String key) {
    if (StringUtils.isBlank(key)) {
      logger.info("传入key为null");
      throw new ServiceException("传入key为null");
    }
    try {
      return stringRedisTemplateForRead.getExpire(key, TimeUnit.SECONDS);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ServiceException("查询redis过期时间异常！");
    }
  }


}
