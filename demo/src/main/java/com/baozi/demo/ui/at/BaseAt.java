package com.baozi.demo.ui.at;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.base.BaseActivity;

@JView(p = DemoPresenter.class, layout = R.layout.at_main)
public class BaseAt extends BaseActivity<DemoPresenter> {

}
