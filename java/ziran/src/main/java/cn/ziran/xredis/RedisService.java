package cn.ziran.xredis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title: RedisService.java
 * @Description: redis基础服务接口
 */
public interface RedisService {

  /**
   * 插入数据
   * 
   * @param key 键
   * @param value 值
   */
  void setData(String key, String value);

  /**
   * 插入数据
   * 
   * @param key 键
   * @param value 值
   * @param seconds 生存时间（秒）
   */
  void setData(String key, String value, int seconds);

  /**
   * 重置生存时间
   * 
   * @param key 键
   * @param seconds 生存时间（秒）
   */
  boolean resetTimeOut(String key, int seconds);

  /**
   * 查询数据
   * 
   * @param key 键
   */
  String getData(String key);

  /**
   * 批量查询数据
   * 
   * @param key[] 键集合
   */
  Collection<String> getDatas(Collection<String> keys);

  /**
   * 删除数据
   * 
   * @param key 键
   */
  void del(String key);

  /**
   * 插入hash
   * 
   * @param key
   * @param hashKey
   * @param value
   */
  void setHashData(String key, String hashKey, String value);

  /**
   * 插入hash
   * 
   * @param key
   * @param hashKey
   * @param value
   * @param 过期时间
   */
  void setHashData(String key, String hashKey, String value, int seconds);

  /**
   * 获取hash
   * 
   * @param key
   * @param hashKey
   * @return
   */
  String getHashData(String key, String hashKey);

  /**
   * 批量获取hash
   * 
   * @param key
   * @param hashKey mapkey集合
   * @return
   */
  Collection<String> getHashDatas(String key, Collection<String> hashKeys);

  List<String> getHashValues(String key);

  /**
   * 删除hash
   * 
   * @param key
   * @param hashKey
   */
  void delHashData(String key, String hashKey);

  /**
   * 是否存在hash
   * 
   * @param key
   * @param hashKey
   * @return true 存在 ；false 不存在
   */
  boolean existsHashKey(String key, String hashKey);

  /**
   * 获取 对应redis key中多有 hash key
   * 
   * @param key
   * @return
   */
  Set<String> getHashKeys(String key);

  /**
   * 插入set
   * 
   * @param key
   * @param value
   * @return 0 失败 ；1成功
   */
  Long setSetData(String key, String value);

  /**
   * 插入set
   * 
   * @param key
   * @param value
   * @param seconds 过期时间
   * @return 0 失败 ；1成功
   */
  Long setSetData(String key, String value, int seconds);

  /**
   * 获取set
   * 
   * @param key
   * @return
   */
  Set<String> getSetDatas(String key);

  /**
   * 是否是set成员
   * 
   * @param key
   * @param o
   * @return true 是set成员 ；false 不是set成员
   */
  boolean isSetMember(String key, String o);

  /**
   * 删除set
   * 
   * @param key
   * @param o
   * @return 0 失败 ；1成功
   */
  Long delSet(String key, String o);

  /**
   * 递增计数器 根据传入的key 查找value并+1
   * 
   * @param key
   * @return 递增后的结果
   */
  Long increment(String key);

  /**
   * 递减计数器 根据传入的key 查找value并-1
   * 
   * @param key
   * @return 递减后的结果
   */
  Long decrement(String key);

  /**
   * 递增hash计数器 根据传入的key hashkey查找value并+1
   * 
   * @param key
   * @param hashKey
   * @return 递增后的结果
   */
  Long increment(String key, String hashKey);

  /**
   * 递减hash计数器 根据传入的key hashkey查找value并-1
   * 
   * @param key
   * @param hashKey
   * @return 递减后的结果
   */
  Long decrement(String key, String hashKey);


  /**
   * 压栈
   * 
   * @param key
   * @param value
   * @return
   */
  Long pushStack(String key, String value);

  /**
   * 出栈
   * 
   * @param key
   * @return
   */
  String popStack(String key);

  /**
   * 入队
   * 
   * @param key
   * @param value
   * @return
   */
  Long inQueue(String key, String value);

  /**
   * 出队
   * 
   * @param key
   * @return
   */
  String outQueue(String key);

  /**
   * 栈/队列长
   * 
   * @param key
   * @return
   */
  Long length(String key);

  /**
   * 范围检索
   * 
   * @param key
   * @param start
   * @param end
   * @return
   */
  List<String> range(String key, int start, int end);

  /**
   * 移除 链表
   * 
   * @param key
   * @param i
   * @param value
   */
  void remove(String key, long i, String value);

  /**
   * 检索链表
   * 
   * @param key
   * @param index
   * @return
   */
  String index(String key, long index);

  /**
   * 链表置值
   * 
   * @param key
   * @param index
   * @param value
   */
  void setLink(String key, long index, String value);

  /**
   * 剪裁
   * 
   * @param key
   * @param start
   * @param end
   */
  void trim(String key, long start, int end);

  /**
   * 获取 hash中的hashkey集合，和value集合
   * 
   * @param key
   * @return 返回Map，hashkey集合=map.get("keys") ；value集合=map.get("values")
   */
  Map<String, List<String>> getHashKeysAndValues(String key);

  /**
   * 判断 redis中是否存在key
   * 
   * @param key 判断的key
   * @return 存在返回true ，不存在返回false
   */
  boolean hasKey(String key);

  /**
   * 查看传入KEY的 剩余生存时间
   * 
   * @param key
   * @return 生存时间 秒
   */
  Long getExpire(String key);
}
