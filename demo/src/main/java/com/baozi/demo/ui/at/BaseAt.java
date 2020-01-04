package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.base.BaseActivity;

@JView(p = DemoPresenter.class, layout = R.layout.at_main)
public class BaseAt extends BaseActivity<DemoPresenter> {

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }
}
