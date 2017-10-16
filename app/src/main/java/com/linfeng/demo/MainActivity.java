package com.linfeng.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.MVPManager;
import com.baozi.mvp.base.TempletActivity;
import com.baozi.mvp.helper.ToolbarHelper;
import com.baozi.mvp.presenter.BasePresenter;
import com.linfeng.demo.contract.MainContract;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

import static com.linfeng.demo.R.layout.activity_main;

public class MainActivity extends TempletActivity<BasePresenter>
        implements MainContract.View {


    @NonNull
    @Override
    protected View onCreateContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, null);
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter<MainContract.View>() {
            private boolean isRTL;

            @Override
            public void onCreate() {
                mView.findView(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isRTL) {
                            isRTL = true;
                            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                        } else {
                            isRTL = false;
                            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                        }
                    }
                });
                mView.getToolbarHelper().setTitle("首页");
                mView.getToolbarHelper().setRightText("213", null);
                //设置滑动效果
                mView.getToolbarHelper().setScrollFlag(R.id.tl_custom, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            }

            @Override
            public void initData() {

            }

            @Override
            public void cancelNetWork() {

            }
        };
    }

    //重写该方法,设置ToolbarLayout
    @Override
    public int initToolbarLayout() {
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
