package com.huiaong.common.exception;

public class BusinessException extends RuntimeException {
    private int status = 500;
    private String message = "unknown exception";

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BusinessException(int status, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(int status, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.status = status;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }
}
