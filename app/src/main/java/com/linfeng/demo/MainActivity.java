package com.linfeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.KeyEvent;

import com.baozi.frame.JBasePresenter;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.mvp.templet.TemplateActivity;
import com.baozi.mvp.templet.helper.ToolbarHelper;


public class MainActivity extends TemplateActivity<BasePresenter> {


    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new JBasePresenter<MainActivity>() {
            @Override
            public void onCreate() {
                mView.getToolbarHelper().setTitle("首页");
                mView.getToolbarHelper().setRightText("213", null);
                //设置滑动效果
                mView.getToolbarHelper().setScrollFlag(R.id.tl_custom, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            }

            @Override
            public void onRefreshData() {

            }

            @Override
            public void netWorkError(Throwable throwable) {

            }
        };
    }

    //重写该方法,设置ToolbarLayout
    @Override
    public int getToolbarLayout() {
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }


    @Override
    public boolean isMaterialDesign() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getToolbarHelper().setTitle("返回了桌面");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
