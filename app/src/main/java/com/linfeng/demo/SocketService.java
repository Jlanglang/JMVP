package com.linfeng.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.linfeng.rx_retrofit_network.location.rxbus.RxBus;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * socket服务
 */
public class SocketService extends Service {
    Socket socket;
    DataOutputStream out;
    InputStream in;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }

    Disposable subscribe;

    @Override
    public void onCreate() {
        super.onCreate();
        subscribe = RxBus.getDefault().toObservable(10086, String.class)
                .observeOn(Schedulers.computation())
                .subscribe(this::sendMsg);
        startSocket();
    }

    /**
     */
    public void sendMsg(String content) {
        if (socket == null) {
            return;
        }
        if (socket.isConnected()) {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(content);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                if (out != null) {
                    try {
                        out.close();
                        socket.close();
                        socket = null;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        }
    }


    private void startSocket() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("192.168.40.72", 9998));
                    in = socket.getInputStream();
                    while (true) {
                        byte[] b = new byte[1024];
                        in.read(b);
                        Log.i("read", new String(b, "utf-8"));
                        Log.i("socket----------", new String(b, "utf-8").trim());
                    }
                } catch (Exception e) {
                    try {
                        if (in != null) {
                            in.close();
                            socket.close();
                            socket = null;
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }.start();
    }
}