package com.linfeng.rx_retrofit_network.converter;

import android.util.Log;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.des.Des;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by dayaa on 16/1/20.
 */
public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private Type type;
    private Charset charset;
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8;");
    private static final String Key = "MAILIANC";

    public GsonRequestBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String json;
        if (value instanceof String) {
            json = Des.encode(Key, value.toString().getBytes());
        } else {
            json = Des.encode(Key, JSONFactory.toJson(value).getBytes());
        }
        Log.i("data", json);
        return new FormBody.Builder()
                .add("json", json)
                .build();
    }
}
