package com.tu.dubbo.serviceImpl;

import com.tu.dubbo.service.IDubboService;
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
public class DubboServiceImpl implements IDubboService {

    private Logger logger = LoggerFactory.getLogger(DubboServiceImpl.class);


    @Override
    public String sayHello(String str) {
        return "Hello"+str;
    }
}