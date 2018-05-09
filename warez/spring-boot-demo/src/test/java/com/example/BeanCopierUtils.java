package com.example;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import org.jboss.netty.handler.codec.serialization.SoftReferenceMap;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yaozhibo on 2016/8/11.
 * 使用cglib复制属性
 * 需要缓存起来BeanCopier否则频繁创建会导致性能问题
 */
public final class BeanCopierUtils {

    /**
     * 采用LRU淘汰算法内存占用小,只保留1000个热点值
     */
    private final static Map<String, BeanCopier> registerMap = new ConcurrentHashMap<>();

    /**
     * added 2016-11-17 11:24:09
     * 提高缓存性能,采用软引用在内存不够时删除缓存.
     */
    private static SoftReferenceMap<String, BeanCopier> softRegisterMap = new SoftReferenceMap<>(new ConcurrentHashMap<>());

    /**
     * LUR只保留1000个热点值
     *
     * @param from 源类型
     * @param to   目标类型
     * @param <T>
     * @param <V>
     */
    public static <T, V> void clonePropertise(T from, V to) {
        String beanKey = from.getClass().getName() + "," + to.getClass().getName();
        BeanCopier copier = registerMap.get(beanKey);
        if (copier == null) {
            copier = BeanCopier.create(from.getClass(), to.getClass(), false);
            //registerMap.put(beanKey, copier);
        }
        copier.copy(from, to, null);
    }

    /**
     * 提高缓存性能,采用软引用在内存不够时删除缓存.
     *
     * @param from Source
     * @param to   Target
     */
    public static <T, V> void copyPropertise(T from, V to) {
        String copyKey = from.getClass().getName() + "," + to.getClass().getName();
        BeanCopier copier = softRegisterMap.get(copyKey);
        if (copier == null) {
            copier = BeanCopier.create(from.getClass(), to.getClass(), false);
            //softRegisterMap.put(copyKey, copier);
        }
        copier.copy(from, to, null);
    }

    /**
     * 需要压力测试
     * 通过getter/setter方法复制对象代替BeanCopier不能复制的复杂对象
     *
     * @param from 源类型
     * @param to   目标类型
     */
    public static Object beanMapCopy(Object from, Object to) {
        BeanMap fromMap = BeanMap.create(from);
        BeanMap toMap = BeanMap.create(to);
        Set keyset = fromMap.keySet();
        for (Object key : keyset) {
            toMap.put(key, fromMap.get(key));
        }
        return toMap.getBean();
    }
}