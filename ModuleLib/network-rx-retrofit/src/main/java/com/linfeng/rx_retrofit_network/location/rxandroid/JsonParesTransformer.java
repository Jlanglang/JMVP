package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jlanglang on 2017/8/31 0031.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */

public class JsonParesTransformer<T> implements Observable.Transformer<BaseResponse<String>, T> {
    private final Class<T> zClass;

    public JsonParesTransformer(Class<T> zClass) {
        this.zClass = zClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Observable<T> call(Observable<BaseResponse<String>> observable) {
        return observable.compose(new SimpleTransformer<String>())
                .flatMap(new Func1<String, Observable<T>>() {
                    @Override
                    public Observable<T> call(String s) {
                        return (Observable) Observable
                                .just(JSONFactory.fromJson(s, zClass));
                    }
                });
    }
}
