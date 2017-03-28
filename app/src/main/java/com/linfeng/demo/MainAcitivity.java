package com.linfeng.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvp.base.TempletActivity;
import com.baozi.mvp.helper.ToolbarHelper;
import com.linfeng.demo.contract.MainContract;

public class MainAcitivity extends TempletActivity<TempletPresenter>
        implements MainContract.View {

    @NonNull
    @Override
    protected View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_main, null);
    }

    //这里偷懒,就不去单独写个PresenterImpl了
    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter<MainContract.View>() {
            @Override
            public void onCreate() {
//                AppBarLayout appBarLayout = getToolbarHelper().getAppBarLayout();
//                ViewGroup.LayoutParams layoutParams = appBarLayout.getLayoutParams();
//                layoutParams.height=320;
//                TabLayout tabLayout = getToolbarHelper().findAppBarView(R.id.tab_layout);
//                for (int i = 0; i < 4; i++) {
//                    tabLayout.addTab(tabLayout.newTab().setText(i + ""));
//                }
                mView.getToolbarHelper().setTitle("首页");
                mView.getToolbarHelper().setRightText("213", null);
                //设置滑动效果
                mView.getToolbarHelper().setScrollFlag(R.id.tl_costom, AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
            }

            @Override
            public void loadData() {

            }

            @Override
            public void cancleNetWork() {

            }
        };
    }

    //重写该方法,设置ToolbarLayout
    @Override
    public int initToolbarLayout() {
        return ToolbarHelper.TOOLBAR_TEMPLET_DEFUATL;
    }


    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public boolean isMaterialDesign() {
        return true;
    }


}
