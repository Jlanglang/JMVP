package com.linfeng.rx_retrofit_network.location.model;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

import java.lang.reflect.Type;

public class StringResponseDeserializer implements JsonDeserializer<BaseResponse<String>> {
    private Gson gson = new Gson();

    @Override
    public BaseResponse<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (json.isJsonObject()) {
            JsonObject asJsonObject = json.getAsJsonObject();
            JsonElement data = asJsonObject.get("data");
            JsonElement status = asJsonObject.get("status");
            JsonElement code = asJsonObject.get("code");
            JsonElement msg = asJsonObject.get("msg");

            baseResponse.setCode(code.getAsInt());
            baseResponse.setStatus(status.getAsBoolean());
            baseResponse.setMsg(msg.getAsString() == null ? "" : msg.getAsString());
            if (data != null) {
                if (data.isJsonArray() || data.isJsonObject()) {
                    String s = gson.toJson(data);
                    baseResponse.setData(s);
                    return baseResponse;
                } else if (data.isJsonNull()) {

                } else {
                    baseResponse.setData(data.getAsString());
                }
            }
        }
        return baseResponse;
    }
}