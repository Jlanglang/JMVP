package com.baozi.homemodle.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baozi.homemodle.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class CustomTestService extends Service {
    @Override
    public void onCreate() {
        Log.i("Kathy", "onCreate - Thread ID = " + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("Kathy", "onDestroy - Thread ID = " + Thread.currentThread().getId());
        super.onDestroy();
    }

    public static final int NOTIFICATION_ID = 1234;

    private static final String TAG = "CustomTestService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> {
            Log.i(TAG, "onStartCommand--" + aLong);
        });
        Log.i(TAG, "onStartCommand");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
            Notification.Builder builder = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("")
                    .setShowWhen(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setChannelId(NOTIFICATION_ID + "")//设置Notification的ChannelID,否则不能正常显示
                    .setContentText("");

            NotificationChannel silentChannel = new NotificationChannel(NOTIFICATION_ID + "", "msg", NotificationManager.IMPORTANCE_HIGH);
            silentChannel.setGroup(NOTIFICATION_ID + "");
            silentChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);//设置在锁屏界面上显示这条通知
            silentChannel.setDescription("信息通知");
            silentChannel.setLightColor(Color.GREEN);
            silentChannel.setName("通知");
            silentChannel.setSound(null, null);
            silentChannel.enableVibration(false);
            silentChannel.enableLights(true);

            startForeground(NOTIFICATION_ID, builder.build());
        } else if (Build.VERSION.SDK_INT < 18) {
            //18以前空通知栏即可
            startForeground(NOTIFICATION_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, CustomTestInnerService.class);
            startService(innerIntent);
            startForeground(NOTIFICATION_ID, new Notification());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private static class CustomTestInnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(NOTIFICATION_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}