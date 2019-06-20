package com.baozi.demo.ui.fg;

import android.os.Bundle;
import android.view.View;

import com.baozi.demo.R;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.base.BaseFragment;
import com.baozi.mvp.presenter.EmptyPresenter;

@JView(layout = R.layout.fg_demo)
public class DemoFg extends BaseFragment<EmptyPresenter> {
    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        findView(R.id.bt_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findView(R.id.bt_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().hide(DemoFg.this).commitAllowingStateLoss();
            }
        });
    }

    @Override
    public boolean isLazy() {
        return true;
    }
}
