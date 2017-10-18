package com.linfeng.rx_retrofit_network.location.rxandroid;

import android.content.Context;
import android.widget.Toast;

import com.linfeng.rx_retrofit_network.factory.NetWorkErrorFactory;

import io.reactivex.functions.Consumer;

/**
 * Created by baozi on 2017/10/18.
 */

public class ErrorToastConsumer implements Consumer<Throwable> {
    private Context applicationContext;

    public ErrorToastConsumer(Context context) {
        applicationContext = context.getApplicationContext();
    }

    @Override
    public void accept(Throwable e) throws Exception {
//        String error = NetWorkErrorFactory.getError(e);
//        String errorMsg;
//        if (e instanceof APIException) {
//            APIException exception = (APIException) e;
//            errorMsg = exception.message;
//        } else if (e instanceof UnknownHostException) {
//            errorMsg = "请开启网络链接";
//        } else if (e instanceof SocketTimeoutException) {
//            errorMsg = "请求超时";
//        } else if (e instanceof ConnectException) {
//            errorMsg = "连接失败,稍后再试";
//        } else if (e instanceof HttpException) {
//            errorMsg = "请求超时";
//        } else {
//            errorMsg = "请求失败";
//        }
        Toast.makeText(applicationContext, NetWorkErrorFactory.getError(e), Toast.LENGTH_SHORT).show();
    }
}
