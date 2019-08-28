package com.tu.service.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.zookeeper.data.Stat;

/**
 * @Description
 * @Classname IZookeeperServive
 * @Date 2019/8/28 10:53
 * @Created by tuyongjian
 */
public interface IZookeeperCuratorServive {

    /**
     *   创建连接
     */
    CuratorFramework getCuratorFramework();


    boolean isStarted();

    /**
     * 创建节点
     */

    String create(String path,String value) throws Exception;

    /**
     * 删除节点
     * @param path
     * @return
     */
    void delete(String path) throws Exception;

    /**
     *更新数据
     */

    Stat update(String path, String value) throws Exception;

    /**
     *查询数据
     * @param path
     * @return
     */
    String getData(String path) throws Exception;



}