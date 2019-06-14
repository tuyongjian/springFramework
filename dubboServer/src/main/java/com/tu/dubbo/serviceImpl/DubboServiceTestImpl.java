package com.tu.dubbo.serviceImpl;

import com.tu.dubbo.service.IDubboService;
import com.tu.dubbo.service.IDubboServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Classname DubboServiceImpl
 * @Date 2019/4/12 10:14
 * @Created by tuyongjian
 */
@Service
public class DubboServiceTestImpl implements IDubboServiceTest {

    private Logger logger = LoggerFactory.getLogger(DubboServiceTestImpl.class);


    @Override
    public String sayHelloTest(String str) {
        return "HelloTest--"+str;
    }
}