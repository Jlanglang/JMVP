package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.text.TextUtils;
import android.widget.Toast;

import com.linfeng.rx_retrofit_network.NetWorkManager;
import com.linfeng.rx_retrofit_network.factory.NetWorkErrorFactory;

import io.reactivex.functions.Consumer;

/**
 * Created by baozi on 2017/10/18.
 */

public enum ToastConsumer implements Consumer<Throwable> {
    INSTANCE;

    @Override
    public void accept(Throwable e) throws Exception {
        String error = NetWorkErrorFactory.disposeError(e);
        if (!TextUtils.isEmpty(error)) {
            Toast.makeText(NetWorkManager.mContext, error, Toast.LENGTH_SHORT).show();
        }
    }
}
