package com.baozi.jmvp.bean;

/**
 * Created by baozi on 2016/12/5.
 */
public class BaseResponse<T> {
    private boolean success;
    private int resultCode;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public  T getData() {
        return data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getMsg() {
        return msg;
    }
}
