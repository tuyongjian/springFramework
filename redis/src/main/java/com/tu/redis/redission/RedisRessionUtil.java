package com.tu.redis.redission;


import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description redis 基于redission 分布式锁的实现
 * @Classname RedisRessionUtil
 * @Date 2019/8/30 15:27
 * @Created by tuyongjian
 */

@Service
public class RedisRessionUtil {

    private Logger logger = LoggerFactory.getLogger(RedisRessionUtil.class);

    //@Autowired
    private RedissonClient redissonClient;


    public void closeRedisson(){
        redissonClient.shutdown();
        logger.info("成功关闭Redis Client连接");
    }

    public String getString(String key) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        return result.get().toString();
    }

    public void setString(String key, Object value) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        if (!result.isExists()) {
            result.set(key, 5, TimeUnit.MINUTES);
        }
    }

    public boolean hasString(String key) {
        RBucket<Object> result = this.redissonClient.getBucket(key);
        if (result.isExists()) {
            return true;
        } else {
            return false;
        }
    }

    public long incr(String key, long delta) {
        return this.redissonClient.getAtomicLong(key).addAndGet(delta);
    }

    /**
     * 获取字符串对象
     * @param objectName
     * @return
     */
    public <T> RBucket<T> getRBucket(String objectName){
        RBucket<T> bucket=redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取Map对象
     * @param objectName
     * @return
     */
    public <K,V> RMap<K, V> getRMap(String objectName){
        RMap<K, V> map=redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     * @param objectName
     * @return
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName){
        RSortedSet<V> sortedSet=redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     * @param objectName
     * @return
     */
    public <V> RSet<V> getRSet(String objectName){
        RSet<V> rSet=redissonClient.getSet(objectName);
        return rSet;
    }

    /**
     * 获取列表
     * @param objectName
     * @return
     */
    public <V> RList<V> getRList(String objectName){
        RList<V> rList=redissonClient.getList(objectName);
        return rList;
    }

    /**
     * 获取队列
     * @param objectName
     * @return
     */
    public <V> RQueue<V> getRQueue(String objectName){
        RQueue<V> rQueue=redissonClient.getQueue(objectName);
        return rQueue;
    }

    /**
     * 获取双端队列
     * @param objectName
     * @return
     */
    public <V> RDeque<V> getRDeque(String objectName){
        RDeque<V> rDeque=redissonClient.getDeque(objectName);
        return rDeque;
    }


    /**
     * 获取锁
     * @param objectName
     * @return
     */
    public RLock getRLock(String objectName){
        RLock rLock=redissonClient.getLock(objectName);
        return rLock;
    }

    /**
     * 释放锁
     * @param
     */
    public void unRLock( RLock rLock){
         rLock.unlock();
    }

    /**
     * 获取原子数
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(String objectName){
        RAtomicLong rAtomicLong=redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取记数锁
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(String objectName){
        RCountDownLatch rCountDownLatch=redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     * @param objectName
     * @return
     */
    public <M> RTopic<M> getRTopic(String objectName){
        RTopic<M> rTopic=redissonClient.getTopic(objectName);
        return rTopic;
    }



}