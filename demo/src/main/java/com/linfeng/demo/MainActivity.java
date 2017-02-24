package com.linfeng.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvpdemo.base.BaseActivity;
import com.baozi.mvpdemo.presenter.BasePresenter;
import com.baozi.mvpdemo.ui.view.UIView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    @Override
    protected View initContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        setMaterialDesignEnabled(true);
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter<UIView>() {
            @Override
            public void onCreate() {
//                mView.getToolbarHelper().setTitle("首页");
//                RecyclerBanner view = mView.findView(R.id.rl_content);
//                RecyclerBanner.BannerEntity bannerEntity = new RecyclerBanner.BannerEntity() {
//                    @Override
//                    public String getUrl() {
//                        return "http://a.hiphotos.baidu.com/image/pic/item/78310a55b319ebc497ee99908026cffc1e171620.jpg";
//                    }
//                };
//                ArrayList<RecyclerBanner.BannerEntity> bannerEntities = new ArrayList<>();
//                bannerEntities.add(bannerEntity);
//                bannerEntities.add(bannerEntity);
//                bannerEntities.add(bannerEntity);
//                bannerEntities.add(bannerEntity);
//                view.setDatas(bannerEntities);
            }

            @Override
            public void LoadData() {

            }
        };
    }

    @Override
    public void isNightMode(boolean isNight) {

    }

    @Override
    public boolean isCustomLayout() {
        return false;
    }

    @Override
    protected int initToolbarLayout() {
        return 0;
    }
}
