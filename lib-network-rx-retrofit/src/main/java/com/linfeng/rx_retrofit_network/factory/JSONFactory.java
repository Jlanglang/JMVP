package com.linfeng.rx_retrofit_network.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.linfeng.rx_retrofit_network.location.model.StringResponseDeserializer;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class JSONFactory {

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(String.class, new StringResponseDeserializer())
            .create();
    public static final JsonParser jsonParser = new JsonParser();

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

    public static JsonElement parseJson(String json) {
        return jsonParser.parse(json);
    }

    public static String getValue(JsonElement json, String key) {
        JsonElement jsonElement = json.getAsJsonObject().get(key);
        if (jsonElement.isJsonNull()) {
            return "";
        }
        return jsonElement.toString();
    }
}
