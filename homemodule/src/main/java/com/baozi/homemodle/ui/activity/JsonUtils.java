package com.baozi.homemodle.ui.activity;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by baozi on 2017/5/8.
 */

public class JsonUtils {
    public <T> T parseJson(String json, Class<T> t) {
        return JSON.parseObject(json, t);
    }
    public <T> List<T> parseArray(String json, Class<T> t) {
        return JSON.parseArray(json, t);
    }
}
