package com.tu.service.serviceImpl;

import com.tu.curd.model.User;
import com.tu.service.service.IExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: tuyongjian
 * @Date: 2019/12/6 10:27
 * @Description:测试多线程
 *
 * InitializingBean bean初始化的时候会执行
 * DisposableBean bean销毁的时候会执行
 */
@Service
public class ExecutorServiceImpl implements IExecutorService , InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(ExecutorServiceImpl.class);

    //定义线程池数量
    @Value("${corePoolSize}")
    private  int corePoolSize;

    private ExecutorService execPool ;
    private CountDownLatch countDownLatch =null;

    //每个线程执行的数量
    private static final int count=1000;

    public void destroy() throws Exception {
        execPool.shutdownNow();
    }

    public void afterPropertiesSet() throws Exception {
        execPool = Executors.newFixedThreadPool(corePoolSize);
    }


    public void task() {
        List<User> list = new ArrayList<User>();
        countDownLatch = new CountDownLatch(list.size());
        for(User users:list) {
             execPool.submit(new HandDate(users));
        }
        try {
            //等计数器为0的时候唤醒主线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class HandDate  extends Thread{
        User user=new User();
        String flag;
        public  HandDate(User user){
            this.user = user;
        }
        public void run() {
            try {

                doTask(user);
            } catch (Exception e) {
                logger.error("-----------"+e);
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public void doTask(User user){
        logger.info("-----------"+user.toString());
    }

}
