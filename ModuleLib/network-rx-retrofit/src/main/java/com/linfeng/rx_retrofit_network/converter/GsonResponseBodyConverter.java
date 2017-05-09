package com.linfeng.rx_retrofit_network.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by dayaa on 16/1/20.
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;

    public GsonResponseBodyConverter() {
    }

    public GsonResponseBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return new Gson().fromJson(value.string(), type);
        } finally {
            value.close();
        }
    }
}
