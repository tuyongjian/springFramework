package com.tu.service.serviceImpl;

import com.tu.service.service.IZookeeperServer;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * @Description
 * @Classname ZookeeperServerImpl
 * @Date 2019/8/27 14:31
 * @Created by tuyongjian
 */
@Service
public class ZookeeperServerImpl implements IZookeeperServer {

    private Logger logger = LoggerFactory.getLogger(ZookeeperServerImpl.class);

    private ZooKeeper zooKeeper;

    public  Boolean isConnection;

    @Value("${zookeeper-connection-url}")
    private String connectionString;

    @Value("${zookeeper-sessionTimeout}")
    private int sessionTimeout;

    /**
    *   获取zookeeper连接
    *
    * */
    public ZooKeeper getZooKeeper() throws Exception {
        try {
            if(zooKeeper==null){
                zooKeeper = new ZooKeeper(connectionString, sessionTimeout, this, false);
                isConnection =true;
            }
        } catch (IOException e) {
            throw new Exception("Create Zookeeper Exception : " + e);
        }
        return zooKeeper;
    }

    public boolean isConnected() {
        return isConnection;
    }


    /**
     * 创建持久性无序节点
     * @param path
     * @param value
     * @return
     */
    public String createNode(String path, String value) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 创建临时无序节点
     * @param path
     * @param value
     * @return
     */
    public String createEphemeralNode(String path, String value) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    /**
     * 创建临时有序节点
     * @param path
     * @param value
     * @return
     */
    public String createEphemeralSequentialNode(String path, String value) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }


    /**
     * 创建永久有序节点
     * @param path
     * @param value
     * @return
     */
    public String createSequentialNode(String path, String value) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    /**
     * 获取路径下面的所有节点
     * @param path
     * @return
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, true);
        return children;
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, true, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path, -1);
    }

    public String getCTime(String path) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, true);
        return String.valueOf(stat.getCtime());
    }

    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException {
        int childenNum = zooKeeper.getChildren(path, true).size();
        return childenNum;
    }

    public void closeConnection() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public Stat exists(String path) throws KeeperException, InterruptedException {
        Stat stat =  zooKeeper.exists(path,true);
        return stat;
    }

    //watch 里面的方法
    public void process(WatchedEvent watchedEvent) {
        logger.info("zookeeper watch----------------[{}]",watchedEvent.toString());
    }


}