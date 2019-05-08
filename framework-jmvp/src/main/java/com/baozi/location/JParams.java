package com.baozi.location;


import com.baozi.linfeng.location.SimpleParams;

/**
 * Created by Jlanglang on 2017/9/5 0005.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */
//统一添加时间戳和token
public class JParams extends SimpleParams {

    public static JParams create() {
        return new JParams();
    }

    @Override
    public JParams putP(String key, Object value) {
        return (JParams) super.putP(key, value);
    }

    @Override
    public JParams putP(String key, Object value, String emptyMessage) {
        return (JParams) super.putP(key, value, emptyMessage);
    }
}
