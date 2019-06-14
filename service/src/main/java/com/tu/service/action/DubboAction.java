package com.tu.service.action;

import com.tu.common.dto.Result;
import com.tu.dubbo.service.IDubboService;
import com.tu.dubbo.service.IDubboServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tuyongjian on 2019/4/14.
 */
@Controller
@RequestMapping(value = "dubbo")
public class DubboAction {

    private Logger logger = LoggerFactory.getLogger(DubboAction.class);

/*    @Autowired
    private IDubboService dubboService;

    @Autowired
    private IDubboServiceTest dubboServiceTest;

    @ResponseBody
    @RequestMapping(value = "index",method = RequestMethod.POST)
    public Result index(){
        Result result = new Result();
        result.setMessage( dubboService.sayHello("world"));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public Result test(){
        Result result = new Result();
        result.setMessage( dubboServiceTest.sayHelloTest("world"));
        return result;
    }*/
}
