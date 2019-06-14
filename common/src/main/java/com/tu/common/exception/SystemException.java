package com.tu.common.exception;

/**
 * Created by tuyongjian on 2019/1/8.
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
