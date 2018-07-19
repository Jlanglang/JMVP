package com.baozi.location;

import android.app.Dialog;
import android.content.DialogInterface;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author jlanglang  2017/7/2 10:39
 * @Change
 */
public class LoadDialogTransformer<T> implements ObservableTransformer<T, T> {
    private Dialog mDialog;

    public LoadDialogTransformer(Dialog dialog, final CompositeDisposable compositeSubscription) {
        mDialog = dialog;
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (compositeSubscription != null) {
                    compositeSubscription.dispose();
                }
            }
        });
    }


    private void dismiss() {
        mDialog.dismiss();
    }

    private void show() {
        mDialog.show();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                show();
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dismiss();
            }
        }).doOnNext(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                dismiss();
            }
        });
    }
}
