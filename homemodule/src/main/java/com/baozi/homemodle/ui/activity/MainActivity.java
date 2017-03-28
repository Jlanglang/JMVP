package com.baozi.homemodle.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.homemodle.presenter.HomeActvityPresenterImpl;
import com.baozi.mvp.base.TempletActivity;
import com.zhy.autolayout.utils.AutoUtils;


public class MainActivity extends TempletActivity<HomeActvityPresenterImpl> {

    @NonNull
    @Override
    public View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_main, null);
        AutoUtils.auto(inflate);
        return inflate;
    }

//    /**
//     * 将ip的整数形式转换成ip形式
//     *
//     * @param ipInt
//     * @return
//     */
//    public static String int2ip(int ipInt) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(ipInt & 0xFF).append(".");//取最后8位
//        sb.append((ipInt >> 8) & 0xFF).append(".");//取倒数第二个8位
//        sb.append((ipInt >> 16) & 0xFF).append(".");//取倒数第三个8位
//        sb.append((ipInt >> 24) & 0xFF);//取倒数第四个8位
//        return sb.toString();
//    }


    @Override
    protected HomeActvityPresenterImpl initPresenter() {
        return new HomeActvityPresenterImpl();
    }

}
