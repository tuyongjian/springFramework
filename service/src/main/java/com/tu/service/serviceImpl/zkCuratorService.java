package com.tu.service.serviceImpl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description curator+zookeeper 实现分布式锁
 * @Classname zkCuratorService
 * @Date 2019/8/28 14:34
 * @Created by tuyongjian
 */
@Component
public class zkCuratorService {

    public static Logger log = LoggerFactory.getLogger(zkCuratorService.class);
    String root = "/lock/";//根节点
    static CuratorFramework curatorFramework;
    @Value("${zookeeper-connection-url}")
    private  String ZK_URL = "";
    /*static  {
        curatorFramework= CuratorFrameworkFactory.newClient("120.55.49.58:2181",new ExponentialBackoffRetry(1000,3));
        curatorFramework.start();
    }*/


    /**
     * 实例化 //可重入排它锁
     * @param lockName
     */
    public InterProcessMutex init(String lockName){
        InterProcessMutex  interProcessMutex=null;
        try {
            interProcessMutex = new InterProcessMutex(curatorFramework, root + lockName);
        }catch (Exception e){
            log.error("initial InterProcessMutex exception="+e);
        }
        return interProcessMutex;
    }

    /**
     * 获取锁
     */
    public boolean acquireLock(InterProcessMutex interProcessMutex){
        try {
            //重试12次
            if(interProcessMutex.acquire(40, TimeUnit.SECONDS)) {
                log.info("Thread:"+Thread.currentThread().getId()+" acquire distributed lock  success");
                return true;
            }
        } catch (Exception e) {
            log.error("distributed lock acquire exception="+e);
            return false;
        }
        log.info("Thread:"+Thread.currentThread().getId()+" acquire distributed lock  fail");
      return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(InterProcessMutex interProcessMutex, String path){
        try {
            if(interProcessMutex != null && interProcessMutex.isAcquiredInThisProcess()){
                interProcessMutex.release();
                curatorFramework.delete().inBackground().forPath(root+path);
                log.info("Thread:"+Thread.currentThread().getId()+" release distributed lock  success");
            }
        }catch (Exception e){
            log.info("Thread:"+Thread.currentThread().getId()+" release distributed lock  exception="+e);
        }
    }

}