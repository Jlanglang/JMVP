package com.baozi.mvpdemo.location.rxandroid;

import com.baozi.mvpdemo.bean.BaseResponse;

import rx.Observable;


public class ModleFilterFactory {
    private static final SimpleTransformer simpleTransformer = new SimpleTransformer();
    @SuppressWarnings("unchecked")
    public static <T> Observable filter(Observable<BaseResponse<T>> observable) {
        return observable.compose(simpleTransformer);
    }

}