package com.tu.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
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

    @Bean(destroyMethod = "shutdown")
    RedissonClient redission(){
        Config config = new Config();
        // 哨兵的配置
        // config.useSentinelServers();
        //单机地址
        config.useSingleServer().setAddress("127.0.0.1:6379");
        return Redisson.create(config);
    }
}