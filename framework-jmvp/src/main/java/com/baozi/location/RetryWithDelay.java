package com.baozi.location;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }


    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (++retryCount <= maxRetries) {
//                            if (throwable instanceof APIException) {
//                                return Observable.timer(retryDelayMillis, TimeUnit.SECONDS)
//                                        .flatMap(new Func1<Long, Observable<?>>() {
//                                            @Override
//                                            public Observable<?> call(Long aLong) {
//                                                return JApiImpl.getToken();
//                                            }
//                                        });
//                            }
                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                            return Observable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }

//                    @Override
//                    public Observable<?> call(Throwable throwable) {
//                        if (++retryCount <= maxRetries) {
////                            if (throwable instanceof APIException) {
////                                return Observable.timer(retryDelayMillis, TimeUnit.SECONDS)
////                                        .flatMap(new Func1<Long, Observable<?>>() {
////                                            @Override
////                                            public Observable<?> call(Long aLong) {
////                                                return JApiImpl.getToken();
////                                            }
////                                        });
////                            }
//                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
//                            return Observable.timer(retryDelayMillis,
//                                    TimeUnit.MILLISECONDS);
//                        }
//                        // Max retries hit. Just pass the error along.
//                        return Observable.error(throwable);
//                    }
//                });
                    ;
                });
    }
}