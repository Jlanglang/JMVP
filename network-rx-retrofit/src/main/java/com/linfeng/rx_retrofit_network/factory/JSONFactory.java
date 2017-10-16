package com.linfeng.rx_retrofit_network.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.model.StringResponseDeserializer;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class JSONFactory {
    private static Type type = new TypeToken<BaseResponse<String>>() {
    }.getType();

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(type, new StringResponseDeserializer())
            .create();

    private JSONFactory() {

    }

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String string, Class<T> tClass) {
        return gson.fromJson(string, tClass);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

}
