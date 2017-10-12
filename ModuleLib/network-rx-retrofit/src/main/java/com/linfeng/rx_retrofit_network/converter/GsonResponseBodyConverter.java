package com.linfeng.rx_retrofit_network.converter;

import android.util.Log;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.des.Des;

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
            String mailianc = Des.decode("MAILIANC", value.string());
            Log.i("data", mailianc);
            return JSONFactory.fromJson(mailianc, type);
        } finally {
            value.close();
        }
    }
}
