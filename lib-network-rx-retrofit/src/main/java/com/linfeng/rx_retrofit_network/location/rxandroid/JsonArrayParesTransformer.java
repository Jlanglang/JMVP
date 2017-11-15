package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;
import com.linfeng.rx_retrofit_network.location.model.ParameterTypeImpl;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
        return upstream.compose(new NetWorkTransformer<String>())
                .observeOn(Schedulers.computation())
                .flatMap(new Function<String, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(String s) throws Exception {
                        ParameterTypeImpl parameterType = new ParameterTypeImpl(List.class, zClass);
                        List<T> list = JSONFactory.fromJson(s, parameterType);
                        return Observable.just(list);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
