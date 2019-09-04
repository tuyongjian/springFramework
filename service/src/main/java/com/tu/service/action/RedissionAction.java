package com.tu.service.action;

import com.tu.common.dto.Result;
import com.tu.redis.redission.RedisRessionUtil;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Classname RedissionAction
 * @Date 2019/8/30 16:20
 * @Created by tuyongjian
 */
@Controller
@RequestMapping(value = "redission")
public class RedissionAction {
    private Logger logger = LoggerFactory.getLogger(RedissionAction.class);

    @Autowired
    RedisRessionUtil redisRessionUtil ;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        Result result = new Result(true,"TEST");
        logger.info("测试ELK again[{}]",result.toString());
         this.redisRessionUtil.setString("aa","aa");
       // logger.info("查询redis结果为---------[{}]",user.toString());
        return "error/403";
    }

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public Result getData() throws Exception {
        Result result = new Result();
        //启动一百个线程同时去抢占cpu，有可能产生并发
        int count = 100;
        //利用发令枪操作
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread() {
                public void run() {


                    //获取锁
                    RLock rLock = redisRessionUtil.getRLock("test");
                    int sleepMillis = (int) (Math.random() * 500);
                    try {
                        rLock.tryLock(10, 30, TimeUnit.SECONDS);//第一个参数代表等待时间，第二是代表超过时间释放锁，第三个代表设置的时间制
                    } catch (InterruptedException e) {
                        logger.info(e.getMessage());
                    }
                    //模拟处理事务
                    try {
                        logger.info("thread "+Thread.currentThread().getId()+"获得锁");
                        Thread.sleep(sleepMillis);

                    } catch (InterruptedException e) {
                        //释放锁
                        rLock.unlock();
                    }finally {
                        //释放锁
                        rLock.unlock();
                    }

                }
            }.start();
            //递减锁存器的计数，如果计数到达零，则释放所有等待的线程
            countDownLatch.countDown();
        }
        try {
            //使线程在锁存器倒计数至零之前一直等待
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.setSuccess(true);
        result.setMessage("查询成功");
        return result;
    }
}