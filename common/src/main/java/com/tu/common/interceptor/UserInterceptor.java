package com.tu.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by len on 2019/1/18.
 * 拦截器学习
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter{

    private Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    //预处理，先处理完处理器之后才处理后续业务逻辑
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("UserInterceptor preHandle is working ----------------------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("UserInterceptor postHandle is working ----------------------------");
    }

}
