package com.tu.service.aop.impl;

import com.tu.service.aop.IAopService;
import org.springframework.stereotype.Service;

/**
 * @Auther: tuyongjian
 * @Date: 2019/11/1 09:51
 * @Description:
 */
@Service
public class AopServiceImpl implements IAopService {

    public String buy(String gift){
        return "bug gift is "+gift;
    }
}
