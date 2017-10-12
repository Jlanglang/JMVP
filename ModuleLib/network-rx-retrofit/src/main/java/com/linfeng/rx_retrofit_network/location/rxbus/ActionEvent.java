package com.linfeng.rx_retrofit_network.location.rxbus;

/**
 * Created by Jlanglang on 2017/8/14 0014.
 */

public class ActionEvent {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }

    private Object action;
}
