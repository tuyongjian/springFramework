package com.tu.service.serviceImpl;

import com.tu.service.service.IZookeeperCuratorServive;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description 使用zookeeper和curator实现zookeeper增删改查
 * @Classname ZookeeperCurator
 * @Date 2019/8/28 10:01
 * @Created by tuyongjian
 */
@Service
public class ZookeeperCuratorServiceImpl implements IZookeeperCuratorServive{

    private Logger logger = LoggerFactory.getLogger(ZookeeperCuratorServiceImpl.class);

    @Value("${zookeeper-connection-url}")
    private String connectionString;

    @Value("${zookeeper-sessionTimeout}")
    private int sessionTimeout;



    //重试策略 1s重试，重试10次
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);

    CuratorFramework curatorFramework = null;

    /**
     * 连接zookeeper
     * @return
     */
    public CuratorFramework getCuratorFramework() {
        //通过工厂建立连接
        if(curatorFramework==null){
            curatorFramework = CuratorFrameworkFactory.builder().connectString(connectionString) //连接地址
                    .sessionTimeoutMs(sessionTimeout)
                    .retryPolicy(retryPolicy)//重试策略
                    .build();
            curatorFramework.start();//开启连接
        }
        return curatorFramework;
    }


    /**
     * 是否启动
     * @return
     */
    public boolean isStarted() {
        return curatorFramework.isStarted();
    }

    /**
     * 创建 临时节点
     * @param path
     * @param value
     * @return
     */
    public String create(String path, String value) throws Exception {
        return curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,value.getBytes());
    }

    /**
     * 递归删除子节点
     * @param path
     * @throws Exception
     */
    public void delete(String path) throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
    }

    /**
     * 更新数据
     * @param path
     * @param value
     * @return
     */
    public Stat update(String path, String value) throws Exception {
        return curatorFramework.setData().forPath(path,value.getBytes());
    }

    public String getData(String path) throws Exception {
        byte[] bytes = curatorFramework.getData().forPath(path);
        if(bytes==null){
            return "";
        }
        return new String(bytes);
    }
}