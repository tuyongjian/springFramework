package com.tu.service.action;

import com.tu.common.dto.Result;
import com.tu.common.util.MD5Util;
import com.tu.curd.model.ShiroPermission;
import com.tu.curd.model.ShiroRole;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.model.ShiroUserRole;
import com.tu.curd.service.IShiroPermissionService;
import com.tu.curd.service.IShiroRoleService;
import com.tu.curd.service.IShiroUserRoleService;
import com.tu.curd.service.IShiroUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by len on 2019/1/23.
 */
@Controller
@RequestMapping(value = "test")
public class TestAction {

    private Logger logger = LoggerFactory.getLogger(TestAction.class);

    @Autowired
    private IShiroUserService shiroUserService;

    @Autowired
    private IShiroRoleService shiroRoleService;

    @Autowired
    private IShiroPermissionService shiroPermissionService;

    @Autowired
    private IShiroUserRoleService shiroUserRoleService;

    @ResponseBody
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public Result insert(){
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setUserName("tuyongjian");
        shiroUser.setIsDelete("0");
        shiroUser.setPassword(MD5Util.md5("123"));
        shiroUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        shiroUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.shiroUserService.addModel(shiroUser);

        ShiroRole shiroRole = new ShiroRole();
        shiroRole.setName("admin");
        shiroRole.setDescription("admin");
        this.shiroRoleService.addModel(shiroRole);

        ShiroPermission shiroPermission = new ShiroPermission();
        shiroPermission.setRoleId(1);
        shiroPermission.setToken("");
        shiroPermission.setUrl("/home");
        shiroPermission.setDescription("home");
        this.shiroPermissionService.addModel(shiroPermission);


        ShiroUserRole shiroUserRole = new ShiroUserRole();
        shiroUserRole.setRoleId(1);
        shiroUserRole.setUserId(1);
        shiroUserRoleService.addModel(shiroUserRole);
        return new Result();
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Result update(){

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(1);
        shiroUser.setUserName("tuyongjian");
        shiroUser.setIsDelete("0");
        shiroUser.setPassword(MD5Util.md5("123"));
        shiroUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        shiroUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.shiroUserService.updateModel(shiroUser);

     /*   ShiroRole shiroRole = new ShiroRole();
        shiroRole.setId(1);
        shiroRole.setName("admin");
        shiroRole.setDescription("admin-1");
        this.shiroRoleService.addModel(shiroRole);

        ShiroPermission shiroPermission = new ShiroPermission();
        shiroPermission.setId(1);
        shiroPermission.setRoleId(1);
        shiroPermission.setToken("1234");
        shiroPermission.setUrl("1234");
        this.shiroPermissionService.addModel(shiroPermission);


        ShiroUserRole shiroUserRole = new ShiroUserRole();
        shiroUserRole.setId(1);
        shiroUserRole.setRoleId(1);
        shiroUserRole.setUserId(2);
        shiroUserRoleService.updateModel(shiroUserRole);
*/
        return new Result();
    }

    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public Result select(){
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(1);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",2);
        ShiroUser shiroUser1 = this.shiroUserService.queryUserAndRole(map);
        ShiroUser shiroUser2 = this.shiroUserService.queryModel(1);
        logger.info("------------------"+shiroUser1.toString());
        logger.info("------------------"+shiroUser1.getShiroUserRoles().toString());
        logger.info("------------------"+shiroUser2.toString());


        ShiroUserRole shiroUserRole = this.shiroUserRoleService.queryModel(1);
        logger.info("------------------"+shiroUserRole.toString());
        return new Result();
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Result delete(){


        return new Result();
    }

    public static void main(String[] args) {
        ShiroUser shiroUser = new ShiroUser();
        ShiroUser shiroUser1 = shiroUser;

        System.out.println(shiroUser.equals(shiroUser1));
    }



}
