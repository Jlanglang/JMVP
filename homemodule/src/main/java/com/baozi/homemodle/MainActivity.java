package com.baozi.homemodle;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baozi.jmvp.base.TempletActivity;
import com.baozi.jmvp.presenter.TempletPresenter;
import com.linfeng.common.utils.HLog;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainActivity extends TempletActivity<TempletPresenter> {

    @NonNull
    @Override
    public View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_main, null);
        AutoUtils.auto(inflate);
        return inflate;
    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");//取最后8位
        sb.append((ipInt >> 8) & 0xFF).append(".");//取倒数第二个8位
        sb.append((ipInt >> 16) & 0xFF).append(".");//取倒数第三个8位
        sb.append((ipInt >> 24) & 0xFF);//取倒数第四个8位
        return sb.toString();
    }

    private Socket socket;

    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter() {
            @Override
            public void onCreate() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            ServerSocket serverSocket = new ServerSocket(8010);
                            // 监听端口，等待客户端连接
                            while (true) {
                                HLog.i("socket", "--等待客户端连接--");
                                Socket socket = serverSocket.accept(); //等待客户端连接
                                HLog.i("socket", "得到客户端连接：" + socket);
                                startReader(socket);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                WifiManager systemService = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo connectionInfo = systemService.getConnectionInfo();
                final int ipAddress = connectionInfo.getIpAddress();
                TextView view = findView(R.id.tv_content);

                new Thread() {
                    @Override
                    public void run() {
                        DataInputStream inputStream;
                        try {
                            socket = new Socket(int2ip(ipAddress), 8010);
                            inputStream = new DataInputStream(socket.getInputStream());
                            while (true) {
                                // 获取读取流
                                String s = inputStream.readUTF();
                                System.out.println("收到消息");
                                System.out.println("获取到服务端的信息:"+s);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                findView(R.id.bt_socket).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**
                         * 发送消息
                         */
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    // socket.getInputStream()
                                    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                                    writer.writeUTF("嘿嘿，你好啊，服务器.."); // 写一个UTF-8的信息
                                    System.out.println("发送消息");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                });
                view.setText("1728161984");
            }

            /**
             * 从参数的Socket里获取最新的消息
             */
            private void startReader(final Socket socket) {

                new Thread() {


                    @Override
                    public void run() {
                        DataInputStream reader;
                        DataOutputStream mDataOutputStream;
                        try {
                            // 获取读取流
                            reader = new DataInputStream(socket.getInputStream());
                            mDataOutputStream = new DataOutputStream(socket.getOutputStream());
                            while (true) {
                                // 读取数据
                                String msg = reader.readUTF();
                                System.out.println("获取到客户端的信息：" + msg);
                                mDataOutputStream.writeUTF("你好,客户端");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void loadData() {

            }

            @Override
            public void cancleNetWork() {

            }

        };
    }

}
