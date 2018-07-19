package com.linfeng.rx_retrofit_network.location.model;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StringResponseDeserializer implements JsonDeserializer<BaseResponse<String>> {
    private Gson gson = new Gson();

    @Override
    public BaseResponse<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (json.isJsonObject()) {
            JsonObject asJsonObject = json.getAsJsonObject();
            JsonElement code = asJsonObject.get("code");
            //JsonElement status = asJsonObject.get("status");
            boolean isMsg = asJsonObject.has("msg");//因为自有日志平台上报log后的结果为{"msg":"ok","code":0},加这样一个过滤
            baseResponse.setCode(code.getAsInt());
            if (isMsg) {
                baseResponse.setMsg("");
                baseResponse.setData("");
                return baseResponse;
            }
            JsonElement data = asJsonObject.get("data");
            JsonElement msg = asJsonObject.get("message");
            //baseResponse.setStatus(status.getAsBoolean());
            baseResponse.setMsg(msg.getAsString() == null ? "" : msg.getAsString());
            if (data != null && !data.isJsonNull()) {
                if (data.isJsonArray() || data.isJsonObject()) {
                    String s = gson.toJson(data);
                    baseResponse.setData(s);
                    return baseResponse;
                } else {
                    baseResponse.setData(data.getAsString());
                }
            } else {
                //data为null,不做处理
                baseResponse.setData("");
            }
        } else if (TextUtils.equals(json.toString(), "\"ok\"")) {//坑爹的日志上报，正式环境返回ok
            baseResponse.setCode(0);
            baseResponse.setData("");
            baseResponse.setMsg("");
        }
        return baseResponse;
    }
}