package com.tu.mq;

import com.tu.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tuyongjian on 2019/3/5.
 */
@Controller
@RequestMapping(value = "kafka")
public class KafkaProducerTest extends BaseController {

    /***
     * 之前直接引入这个服务,没有注解引入,导致KafkaTemplate一直注入不成功，
     * 报空指针异常，日了狗了。
     */
    @Autowired
    KafkaProducerServer kafkaProducerServer;

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public String test(){
        String topic = "spring-kafka-test";
        String value = "test";
        String ifPartition = "0";
        Integer partitionNum = 1;
        String role = "test";//用来生成key
        Map<String,Object> res = kafkaProducerServer.sendMesForTemplate(topic, value, ifPartition, partitionNum, role);


        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);

        return null;
    }
}
