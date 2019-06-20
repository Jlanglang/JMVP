package com.baozi.demo.ui.at;

import com.baozi.demo.R;
import com.baozi.demo.persenter.DemoPresenter;
import com.baozi.mvp.JView;
import com.baozi.mvp.base.BaseActivity;

@JView(layout = R.layout.activity_main,p = DemoPresenter.class)
public class BaseAt extends BaseActivity<DemoPresenter> {

//    @Override
//    protected int initView(@Nullable Bundle savedInstanceState) {
//        return R.layout.activity_main;
//    }
//
//    @NonNull
//    @Override
//    protected DemoPresenter initPresenter() {
//        return new DemoPresenter();
//    }
}
