package com.linfeng.rx_retrofit_network.location;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;

import java.util.HashMap;

/**
 * Created by Jlanglang on 2017/9/4 0004.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */

public class SimpleParams extends HashMap<String, Object> {

    public static SimpleParams create() {
        return new SimpleParams();
    }

    public SimpleParams putP(String key, Object value) {
        this.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return JSONFactory.toJson(this);
    }
}
