package com.tu.service.action;

import com.tu.common.controller.BaseController;
import com.tu.common.dto.Result;
import com.tu.common.util.MD5Util;
import com.tu.curd.model.ShiroUser;
import com.tu.curd.service.IShiroUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by len on 2019/1/24.
 */
@Controller
@RequestMapping(value = "shiro")
public class ShiroAction extends BaseController{
    private Logger logger = LoggerFactory.getLogger(ShiroAction.class);
    @Autowired
    private IShiroUserService shiroUserService;


    //登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/shiro/login";
    }

    //登录成功
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String success(){
        return "/shiro/success";
    }

    //认证未通过访问的页面，即经过认证但是没有相应的权限时跳转的页面 s
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "/shiro/error";
    }


    //登录认证
     @RequestMapping(value = "/shiro-login",method = RequestMethod.GET)
     public String shiroLogin(@RequestParam(value = "username",required = false) String username,
                         @RequestParam(value = "password",required = false) String password,
                          HttpServletRequest request) throws Exception {

        if(StringUtils.isBlank(username) && StringUtils.isBlank(password)){
            return "/shiro/login";
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                //执行认证操作.
                subject.login(token);
            }catch (AuthenticationException ae) {
                logger.info("登陆失败--------------------------------: " + ae.getMessage());
                return "/shiro/login";
            }
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if(savedRequest!=null){
                return "redirect:"+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+savedRequest.getRequestUrl();
            }else{
                return "/shiro/success";
            }
        }
     }

    //登出
    @ResponseBody
    @RequestMapping(value = "/shiro-logout",method = { RequestMethod.POST,RequestMethod.GET})
    public Result shiroLogout(){
       return new Result(true,"logout is success");
    }
}
