package com.tu.common.exception;

/**
 * Created by tuyongjian on 2019/1/8.
 * 异步异常
 */
public class AjaxException extends RuntimeException {

    public AjaxException(String message) {
        super(message);
    }
    public AjaxException() {
        super();
    }

}
