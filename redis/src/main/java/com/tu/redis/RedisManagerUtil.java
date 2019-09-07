package com.tu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class RedisManagerUtil {

    private static JedisSentinelPool pool = null;
    // 自带的哨兵模式 JedisSentinelPool, 并在一开始初始化连接池
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(Integer.valueOf(1000));
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(Integer.valueOf(20));
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMinEvictableIdleTimeMillis(Integer.valueOf(-1));
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(Boolean.valueOf(true));

            // master名称和配置文件中配置的要一样
            String master = "mymaster";
            //setinel客户端提供了master自动发现功能
            Set<String> sentinels = new HashSet<String>();
            sentinels.add("192.168.190.131:26379");
            sentinels.add("192.168.190.131:26380");
            sentinels.add("192.168.190.131:26381");

            pool = new JedisSentinelPool(master, sentinels, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建redis连接池
     *
     * @return JedisPool
     */
    public static JedisSentinelPool getPool() {
        return pool;
    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param redis
     */
    public static void returnResource(JedisSentinelPool pool, Jedis redis) {
        if (redis != null) {
            try {
                pool.returnResource(redis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试redis线程池是否正常
     * @param args
     */
    public static void main(String[] args) {
        JedisSentinelPool pool = RedisManagerUtil.getPool();
        Jedis redis = pool.getResource();
        System.out.println("redis = " + redis);

        if(redis != null){
            returnResource(pool,redis);
        }
    }
}
