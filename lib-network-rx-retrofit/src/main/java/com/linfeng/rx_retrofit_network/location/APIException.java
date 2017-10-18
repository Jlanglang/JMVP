package com.linfeng.rx_retrofit_network.location;

/**
 * 自定义异常，当接口返回的code不为200时，需要跑出此异常
 * eg：登陆时验证码错误；参数为传递等
 */
public class APIException extends Exception {
    private int code;
    private String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

