package com.baozi.mvp.location;

/**
 * 自定义异常，当接口返回的code不为200时，需要跑出此异常
 * eg：登陆时验证码错误；参数为传递等
 */
public class APIException extends Exception {
    public int code;
    public String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

