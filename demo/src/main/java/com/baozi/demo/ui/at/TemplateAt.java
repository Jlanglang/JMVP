package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.tempalet.TemplateActivity;

@JView(p = DemoPresenter.class, layout = R.layout.at_template)
public class TemplateAt extends TemplateActivity<DemoPresenter> {
    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
        getToolbarHelper().setTitle("我是模板Activity")
                .setLeading("关闭")
                .setCanBack(true);
    }
}
