package com.tu.service.action;

import com.tu.common.controller.BaseController;
import com.tu.common.dto.Result;
import com.tu.curd.model.User;
import com.tu.curd.service.IUserService;
import com.tu.redis.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created by tuyongjian on 2019/1/6.
 * 用户Action
 */
@Controller
@RequestMapping(value = "user")
public class UserAction extends BaseController{

    private Logger logger = LoggerFactory.getLogger(UserAction.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Value("${tu}")
    private String tu;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        Result result = new Result(true,tu);
        logger.info("------------[{}]",result.toString());
        return "error/403";
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        Result result = new Result(true,"TEST");
        logger.info("测试ELK again[{}]",result.toString());
      /*  Object user =  this.redisCacheUtil.get("user");
        logger.info("查询redis结果为---------[{}]",user.toString());*/
        return "error/403";
    }

    @ResponseBody
    @RequestMapping(value = "queryUser",method = RequestMethod.POST)
    public User queryUser(ModelMap model,
                      @RequestParam(value = "id") int id,
                          HttpServletResponse response){
        User user  = this.userService.queryUser(id);
        logger.info("queryUser is [{}]",user.toString());
        //this.redisCacheUtil.set("user",user,1000);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "queryCount",method = RequestMethod.POST)
    public int queryUser(){
        //int count  = this.userService.queryCount();
        int count  = this.userService.procedure();
        logger.info("queryUser count is [{}]",count);
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public Result addUser(ModelMap model){
        User user  = new User();
        user.setUserName("屠永建");
        user.setPassword("123");
        user.setMobile("18221483894");
        user.setAddress("安徽省阜阳市颍上县");
        user.setEmail("123123@qq.com");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        user.setRemark("测试");
        userService.addUser(user);
        return new Result(true,"插入成功");
    }

    @ResponseBody
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public Result updateUser(ModelMap model){
        User user  = new User();
        user.setId(1);
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        user.setRemark("测试update");
        userService.updateUser(user);
        return new Result(true,"更新成功");
    }
}
