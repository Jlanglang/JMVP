package com.linfeng.rx_retrofit_network.location.model;

/**
 * Created by baozi on 2016/12/5.
 */
public class BaseResponse<T> {

//        private boolean status;//是否成功
    private int code;//请求状态码,比如1000是成功,其他是失败原因
    private String message;//请求返回的消息,如果成功则为""
    private T data;//data内容


//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public void setData(T data) {
        this.data = data;
    }


//    public boolean isStatus() {
//        return status;
//    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public T getData() {
        return data;
    }


}
