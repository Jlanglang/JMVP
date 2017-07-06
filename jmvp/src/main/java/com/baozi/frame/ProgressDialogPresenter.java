package com.baozi.frame;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.baozi.mvp.ui.BaseView;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author jlanglang  2016/12/17 10:59
 * @版本 2.0
 * @Change
 */

public abstract class ProgressDialogPresenter<T extends BaseView> extends JBasePresenter<T> {
    private Dialog loading;

    /**
     * 使用默认的Dialog
     */
    public Dialog getLoadingDialog() {
        if (loading == null) {
            loading = new ProgressDialog.Builder(mView.getContext()).create();
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setLabel("请稍候...")
//                    .setCancellable(true)
//                    .setAnimationSpeed(2)
//                    .setDimAmount(0.5f);
            loading.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mCompositeSubscription.unsubscribe();
                }
            });
        }
        return loading;
    }

    /**
     * 自定义dialog
     *
     * @param kProgressHUD
     */
    public void setLoadingDialog(Dialog kProgressHUD) {
        this.loading = kProgressHUD;
        loading.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mCompositeSubscription.unsubscribe();
            }
        });
    }

    /**
     * 对Observable进行修改,可以弹出ProgressDialog
     */
    public <R> Observable<R> loadingSubscribe(Observable<R> observable) {
        return observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getLoadingDialog().show();
            }
        }).doOnNext(new Action1<R>() {
            @Override
            public void call(R r) {
                getLoadingDialog().dismiss();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                getLoadingDialog().dismiss();
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getLoadingDialog().dismiss();
            }
        });
    }

}
