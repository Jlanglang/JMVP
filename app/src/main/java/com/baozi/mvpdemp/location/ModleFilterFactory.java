package com.baozi.mvpdemp.location;

import com.baozi.mvpdemp.bean.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 *
 */
public class ModleFilterFactory {
    private static final SimpleTransformer simpleTransformer = new SimpleTransformer();
    @SuppressWarnings("unchecked")
    public static <T> Observable filter(Observable<BaseResponse<T>> observable) {
        return observable.compose(simpleTransformer);
    }
}
