package com.tu.service.action;

import com.tu.service.aop.IAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: tuyongjian
 * @Date: 2019/11/1 10:49
 * @Description:
 */
@Controller
@RequestMapping(value = "aop")
public class AopAction {

    @Autowired
    private IAopService aopService;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        String str = aopService.buy("aa");
        return str;
    }
}
