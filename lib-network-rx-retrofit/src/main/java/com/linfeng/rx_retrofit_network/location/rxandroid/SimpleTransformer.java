package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.linfeng.rx_retrofit_network.location.APIException;
import com.linfeng.rx_retrofit_network.location.model.BaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import rx.functions.Func1;

/**
 * @param <T>
 */
public class SimpleTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {
    private static final int Defautl_TIME_OUT = 5;
    private static final int DEFAUTL_RETRY = 5;

    /**
     * 处理请求结果,BaseResponse
     *
     * @param response 请求结果
     * @return 过滤处理, 返回只有data的Observable
     */
    private Observable<T> flatResponse(final BaseResponse<T> response) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                if (!e.isDisposed()) {
                    if (response.getCode() == 0) {//请求成功
                        e.onNext(response.getData());
                    } else {//请求失败
                        e.onError(new APIException(response.getCode(), response.getMsg()));
                        return;
                    }
                }
                if (!e.isDisposed()) {
                    //请求完成
                    e.onComplete();
                }
            }
///
        });
    }

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .timeout(Defautl_TIME_OUT, TimeUnit.SECONDS)
                .retry(DEFAUTL_RETRY)
                .flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        return flatResponse(tBaseResponse);
                    }
                });
    }
}