package com.tu.service.action;

import com.tu.common.controller.BaseController;
import com.tu.common.dto.Result;
import com.tu.service.service.IZookeeperServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.ZooKeeper;
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
@RequestMapping(value = "zookeeper")
public class ZookeeperAction extends BaseController {

    @Autowired
    private IZookeeperServer zookeeperServer;


    @ResponseBody
    @RequestMapping(value = "getData",method = RequestMethod.POST)
    public Result getData() throws Exception {
            zookeeperServer.getZooKeeper();
        Stat path1Data = null;
        Stat path2Data = null;
        String data1 = "";
        String data2 = "";
        if (this.zookeeperServer.isConnected()){
             path1Data = this.zookeeperServer.exists("/path1");
             path2Data = this.zookeeperServer.exists("/path4");
            if(path1Data==null){
                this.zookeeperServer.createEphemeralNode("/path1","test1");
            }
            if(path2Data==null){
                this.zookeeperServer.createEphemeralSequentialNode("/path4","test2");
            }

            data1 = this.zookeeperServer.getData("/path1");
           // data2 = this.zookeeperServer.getData("/path20000000080");
        }

        Result result = new Result();
        result.setData("data1--:"+data1+"data2--:"+data2);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "updateData",method = RequestMethod.POST)
    public Result updateData() throws Exception {
        zookeeperServer.getZooKeeper();
        Stat path1Data = null;
        Stat path2Data = null;
        String data1 = "";
        String data2 = "";
        if (this.zookeeperServer.isConnected()){
            path1Data = this.zookeeperServer.exists("/path1");
            path2Data = this.zookeeperServer.exists("/path2");
            if(path1Data==null){
                this.zookeeperServer.createEphemeralNode("/path1","test1");
            }else{
                this.zookeeperServer.setData("/path1","path1");
            }
            if(path2Data==null){
                this.zookeeperServer.createEphemeralSequentialNode("/path2","test2");
            }else{
                this.zookeeperServer.setData("/path2","path2");
            }

            data1 = this.zookeeperServer.getData("/path1");
            data2 = this.zookeeperServer.getData("/path2");
        }

        Result result = new Result();
        result.setData("data1--:"+data1+"data2--:"+data2);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "deleteData",method = RequestMethod.POST)
    public Result deleteData() throws Exception {
        zookeeperServer.getZooKeeper();
        Stat path1Data = null;
        Stat path2Data = null;
        String data1 = "";
        String data2 = "";
        if (this.zookeeperServer.isConnected()){
            path1Data = this.zookeeperServer.exists("/path1");
            path2Data = this.zookeeperServer.exists("/path2");
            if(path1Data!=null){
                this.zookeeperServer.deleteNode("/path1");
            }
            if(path2Data!=null){
                this.zookeeperServer.deleteNode("/path2");
            }
        }

        Result result = new Result();
        result.setData("delete success");

        return result;
    }



    @ResponseBody
    @RequestMapping(value = "getAllNode",method = RequestMethod.POST)
    public Result getAllNode() throws Exception {
        zookeeperServer.getZooKeeper();
        Result result = new Result();
        if (this.zookeeperServer.isConnected()){
             List<String> list =  this.zookeeperServer.getChildren("/");
            result.setData(list);
        }
        return result;
    }


}