package com.tu.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description redission 配置
 * @Classname RedisRessionConfig
 * @Date 2019/8/30 15:50
 * @Created by tuyongjian
 */
//@Configuration
public class RedisRessionConfig {

    @Value("${redis.host}")
    private String address;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.sentinel.nodes}")
    private String sentinelNode;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redission(){
        Config config = new Config();
        // 哨兵的配置
        config.useSentinelServers().setMasterName(master);
        config.useSentinelServers().addSentinelAddress("127.0.0.1:26379").
                addSentinelAddress("127.0.0.1:26380").addSentinelAddress("127.0.0.1:26381");
        //单机地址
        //config.useSingleServer().setAddress("127.0.0.1:6379");
        return Redisson.create(config);
    }
}