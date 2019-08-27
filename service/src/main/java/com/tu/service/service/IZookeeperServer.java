package com.tu.service.service;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @Description
 * @Classname IZookeeperServer
 * @Date 2019/8/27 14:31
 * @Created by tuyongjian
 */
public interface IZookeeperServer extends Watcher {

    public ZooKeeper getZooKeeper() throws Exception;

    /**
     * 创建永久无序节点
     * @param path
     * @param value
     * @return
     */
    public String createNode(String path, String value) throws KeeperException, InterruptedException;

    /**
     * 创建临时无序节点
     * @param path
     * @param value
     * @return
     */
    public String createEphemeralNode(String path, String value) throws KeeperException, InterruptedException;

    /**
     * 创建临时有序节点
     * @param path
     * @param value
     * @return
     */
    public String createEphemeralSequentialNode(String path, String value) throws KeeperException, InterruptedException;

    /**
     * 创建永久有序节点
     * @param path
     * @param value
     * @return
     */
    public String createSequentialNode(String path, String value) throws KeeperException, InterruptedException;


    /**
     * 获取路径下面的所有节点
     * @param path
     * @return
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException;


    /**
     * 获取节点上面的数据
     * @param path
     * @return
     */
    public String getData(String path) throws KeeperException, InterruptedException;

    /**
     * 设置节点信息
     * @param path
     * @param data
     * @return
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException;


    /**
     * 删除节点
     * @param path
     */
    public void deleteNode(String path) throws KeeperException, InterruptedException;


    /**
     * 获取创建时间
     * @param path
     * @return
     */
    public String getCTime(String path) throws KeeperException, InterruptedException;


    /**
     * 获取节点下面的孩子数量
     * @param path
     * @return
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException;


    /**
     * 关闭连接
     */
    public void closeConnection() throws InterruptedException;

    /**
     * 节点是否存在
     * @param path
     * @return
     */
    public Stat exists(String path) throws KeeperException, InterruptedException;

    public boolean isConnected();


}