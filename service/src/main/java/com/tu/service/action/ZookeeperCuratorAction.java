package com.tu.service.action;

import com.tu.common.controller.BaseController;
import com.tu.common.dto.Result;
import com.tu.service.service.IZookeeperCuratorServive;
import com.tu.service.service.IZookeeperServer;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Classname Zookeeper
 * @Date 2019/8/27 14:28
 * @Created by tuyongjian
 */
@Controller
@RequestMapping(value = "zookeeperCurator")
public class ZookeeperCuratorAction extends BaseController {

    @Autowired
    private IZookeeperCuratorServive zookeeperCuratorServive;


    @ResponseBody
    @RequestMapping(value = "getConnecton",method = RequestMethod.POST)
    public Result getConnecton() throws Exception {
       zookeeperCuratorServive.getCuratorFramework();
        Result result = new Result();

        boolean isStart  = zookeeperCuratorServive.isStarted();
        result.setSuccess(isStart);
        result.setMessage("连接成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Result create() throws Exception {
        zookeeperCuratorServive.getCuratorFramework();
        Result result = new Result();
        boolean isStart  = zookeeperCuratorServive.isStarted();
        if(isStart){
            zookeeperCuratorServive.create("/path1/path2","test");
            result.setSuccess(isStart);
            result.setMessage("添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result update() throws Exception {
        zookeeperCuratorServive.getCuratorFramework();
        Result result = new Result();
        boolean isStart  = zookeeperCuratorServive.isStarted();
        if(isStart){
            zookeeperCuratorServive.update("/path1/path2","1111");
            result.setSuccess(isStart);
            result.setMessage("更新成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public Result getData() throws Exception {
        zookeeperCuratorServive.getCuratorFramework();
        Result result = new Result();
        boolean isStart  = zookeeperCuratorServive.isStarted();
        if(isStart){
            String msg = zookeeperCuratorServive.getData("/path1/path2");
            result.setSuccess(isStart);
            result.setMessage("查询成功");
            result.setData(msg);
        }
        return result;
    }


}