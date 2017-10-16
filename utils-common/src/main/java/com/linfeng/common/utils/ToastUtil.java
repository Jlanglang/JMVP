package com.linfeng.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jonny on 2015/12/26.
 */
public class ToastUtil {
    public static void showToast(final Context context, final String msg) {
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
