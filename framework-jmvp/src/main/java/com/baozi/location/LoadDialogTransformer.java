package com.baozi.location;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * @author jlanglang  2017/7/2 10:39
 * @Change
 */
public class LoadDialogTransformer<T> implements Observable.Transformer<T, T> {
    private Dialog mDialog;

    public LoadDialogTransformer(Dialog dialog, final CompositeSubscription compositeSubscription) {
        mDialog = dialog;
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (compositeSubscription != null) {
                    compositeSubscription.unsubscribe();
                }
            }
        });
    }

    @Override
    public Observable<T> call(final Observable<T> o) {
        return o.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                show();
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismiss();
            }
        }).doOnNext(new Action1<T>() {
            @Override
            public void call(T t) {
                dismiss();
            }
        });
    }

    private void dismiss() {
        mDialog.dismiss();
    }

    private void show() {
        mDialog.show();
    }
}
