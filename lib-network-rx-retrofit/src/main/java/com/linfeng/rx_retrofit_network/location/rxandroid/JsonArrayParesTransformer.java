package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.factory.JSONFactory;
import com.linfeng.rx_retrofit_network.location.model.ParameterTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jlanglang on 2017/8/31 0031.
 * 简书:http://www.jianshu.com/u/6bac141ea5fe
 */
public class JsonArrayParesTransformer<T> implements ObservableTransformer<String, List<T>> {
    private Class<T> zClass;

    public JsonArrayParesTransformer(Class<T> zClass) {
        this.zClass = zClass;
    }


    @Override
    public ObservableSource<List<T>> apply(Observable<String> upstream) {
        return upstream.compose(new NetWorkTransformer())
                .observeOn(Schedulers.computation())
                .flatMap(s -> {
                    s = "".equals(s) ? "[]" : s;
                    ParameterTypeImpl parameterType = new ParameterTypeImpl(List.class, new Type[]{zClass});
                    List<T> list = JSONFactory.fromJson(s, parameterType);
                    return Observable.just(list);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
