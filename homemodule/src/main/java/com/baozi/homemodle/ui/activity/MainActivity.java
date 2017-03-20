package com.baozi.homemodle;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baozi.jmvp.base.JBaseActivity;
import com.baozi.jmvp.base.TempletActivity;
import com.baozi.jmvp.presenter.TempletPresenter;
import com.linfeng.common.utils.ToastUtil;
import com.zhy.autolayout.utils.AutoUtils;


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


    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter() {
            @Override
            public void onCreate() {
                WifiManager systemService = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo connectionInfo = systemService.getConnectionInfo();
                int ipAddress = connectionInfo.getIpAddress();
                TextView view = findView(R.id.tv_content);
//                view.setText(int2ip(ipAddress));
//                String str = "192.168.1.103";
//                String[] split = str.split("\\.");
//                int ip = 0;
//                for (int i = 0; i < split.length; i++) {
//                    int i1 = Integer.parseInt(split[i]);
//                    int i2 = i1 << (8 * i);
//                    ip += i2;
//                }
//                ToastUtil.showToast(mView.getContext(), ip + "");
                view.setText("1728161984");
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
