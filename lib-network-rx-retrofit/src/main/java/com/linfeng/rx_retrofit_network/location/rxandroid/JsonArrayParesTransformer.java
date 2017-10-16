package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.model.ParameterTypeImpl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by Jlanglang on 2017/8/31 0031.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */
public class JsonArrayParesTransformer<T> implements ObservableTransformer<BaseResponse<String>, List<T>> {
    private Class<T> zClass;

    public JsonArrayParesTransformer(Class<T> zClass) {
        this.zClass = zClass;
    }


    @Override
    public ObservableSource<List<T>> apply(Observable<BaseResponse<String>> upstream) {
        return upstream
                .compose(new SimpleTransformer<String>())
                .flatMap(new Function<String, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(String s) throws Exception {
                        ParameterTypeImpl parameterType = new ParameterTypeImpl(List.class, zClass);
                        List<T> list = JSONFactory.fromJson(s, parameterType);
                        return Observable.just(list);
                    }
                });
//                .flatMap(new Func1<String, Observable<List<T>>>() {
//                    @Override
//                    public Observable<List<T>> call(String s) {
//
//                    }
//                });
//                ;
    }
}
