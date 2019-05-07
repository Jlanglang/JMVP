package com.linfeng.rx_retrofit_network.location;

/**
 * 自定义异常，当接口返回的code不为200时，需要跑出此异常
 * eg：登陆时验证码错误；参数为传递等
 */
public class APIException extends Exception {
    private String code;
    private String message;
    private String responseData;

    public APIException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public APIException(String code, String message, String responseData) {
        this.responseData = responseData;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getBaseResponse() {
        return responseData;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

