package com.tu.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description redis 哨兵模式配置
 * @Classname RedisSentinelConfig
 * @Date 2019/8/29 10:43
 * @Created by tuyongjian
 */
public class RedisSentinelConfig extends RedisSentinelConfiguration {

    public RedisSentinelConfig(String master,String sentinel) {
        if (StringUtils.isEmpty(master) || StringUtils.isEmpty(sentinel)) {
            throw new IllegalArgumentException("master or sentinel not config");
        }
        //master
        setMaster(master);
        //sentinel
        String[] hps = sentinel.split(",");
        if(hps.length < 1){
            throw new IllegalArgumentException("no sentinel hosts parse");
        }
        Set<RedisNode> sNodes = new HashSet<RedisNode>();
        for (String hp : hps) {
            String[] h = hp.split(":");
            RedisNode sNode = new RedisNode(h[0], Integer.valueOf(h[1]));
            sNodes.add(sNode);
        }
        setSentinels(sNodes);
    }
}