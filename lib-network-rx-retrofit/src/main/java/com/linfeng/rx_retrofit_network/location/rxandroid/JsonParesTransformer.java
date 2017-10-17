package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by Jlanglang on 2017/8/31 0031.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */

public class JsonParesTransformer<T> implements ObservableTransformer<BaseResponse<String>, T> {
    private final Class<T> zClass;

    public JsonParesTransformer(Class<T> zClass) {
        this.zClass = zClass;
    }

//    @Override
//    @SuppressWarnings("unchecked")
//    public Observable<T> call(Observable<BaseResponse<String>> observable) {
//        return  observable.compose(new NetWorkTransformer<String>())
//                .flatMap(new Func1<String, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(String s) {
//                        return (Observable) Observable
//                                .just(JSONFactory.fromJson(s, zClass));
//                    }
//                });
//    }

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<String>> upstream) {
        return upstream.compose(new NetWorkTransformer<String>())
                .flatMap(new Function<String, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(String s) throws Exception {
                        return  Observable
                                .just(JSONFactory.fromJson(s, zClass));
                    }
//                    @Override
//                    public Observable<T> call(String s) {
//                        return (Observable) Observable
//                                .just(JSONFactory.fromJson(s, zClass));
//                    }
                });
    }
}
