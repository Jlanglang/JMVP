package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.model.ParameterTypeImpl;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jlanglang on 2017/8/31 0031.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */
public class JsonArrayParesTransformer<T> implements Observable.Transformer<BaseResponse<String>, List<T>> {
    private Class<T> zClass;

    public JsonArrayParesTransformer(Class<T> zClass) {
        this.zClass = zClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Observable<List<T>> call(Observable<BaseResponse<String>> observable) {
        return observable
                .compose(new SimpleTransformer<String>())
                .flatMap(new Func1<String, Observable<List<T>>>() {
                    @Override
                    public Observable<List<T>> call(String s) {
                        ParameterTypeImpl parameterType = new ParameterTypeImpl(List.class, zClass);
                        return (Observable) Observable
                                .just(JSONFactory.fromJson(s, parameterType));
                    }
                });
    }

}
