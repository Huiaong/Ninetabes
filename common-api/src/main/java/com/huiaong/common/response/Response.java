package com.huiaong.common.response;

import com.google.common.base.MoreObjects;

import java.io.Serializable;


public class Response<T> implements Serializable {

    private T result;
    private String error;
    private Integer code;

    public Response() {
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response<>();
        resp.setResult(data);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> fail(String error) {
        Response<T> resp = new Response<>();
        resp.setError(error);
        return resp;
    }

    public Boolean isSuccess() {
        return this.code == 20000;
    }


    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.code = 20000;
        this.result = result;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.code = 50000;
        this.error = error;
    }

    public void setError(String error, Integer code) {
        this.code = code;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("result", this.result).add("error", this.error).add("code", this.code).omitNullValues().toString();
    }
}
