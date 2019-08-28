package com.tu.service.action;

import com.tu.common.dto.Result;
import com.tu.service.serviceImpl.zkCuratorService;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Classname zkCuratorAction
 * @Date 2019/8/28 14:56
 * @Created by tuyongjian
 */
@Controller
@RequestMapping(value = "zkCurator")
public class zkCuratorAction {

    @Autowired
    zkCuratorService zkCuratorService;

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
                    //实例化
                    InterProcessMutex interProcess =zkCuratorService.init("test");
                    //获取锁
                    boolean acquire = zkCuratorService.acquireLock(interProcess);
                    if(acquire){
                        //模拟处理事务
                        int sleepMillis = (int) (Math.random() * 500);
                        try {
                            Thread.sleep(sleepMillis);
                        } catch (InterruptedException e) {
                            //释放锁
                            zkCuratorService.releaseLock(interProcess,"test");
                        }finally {
                            //释放锁
                            zkCuratorService.releaseLock(interProcess,"test");
                        }
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


    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public Result test() throws Exception {
        Result result = new Result();

        result.setSuccess(true);
        result.setMessage("查询成功");
        return result;
    }
}