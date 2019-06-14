package com.tu.common.controller;

import com.tu.common.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tuyongjian on 2019/1/8.
 * 公共的controller 主要是针对异常的处理
 */
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 异常控制，可以根据不同的异常类型 在此定义不同的错误消息和操作
     * */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        logger.info("统一请求异常----[{}]",ex);
        return new ModelAndView().addObject(new Result(false,ex.getMessage()));
    }
}
