package com.linfeng.rx_retrofit_network.location.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by baozi on 2017/6/21.
 */

public class SimpleCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
    }


    @Override
    public void onFailure(Call<T> call, Throwable e) {
        e.printStackTrace();
    }
}
