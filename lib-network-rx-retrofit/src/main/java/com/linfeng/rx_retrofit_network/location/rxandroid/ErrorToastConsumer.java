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
        Toast.makeText(applicationContext, NetWorkErrorFactory.getError(e), Toast.LENGTH_SHORT).show();
    }
}
