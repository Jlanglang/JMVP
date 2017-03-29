package com.baozi.homemodle.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.HomeActvityContract;
import com.baozi.homemodle.presenter.HomeActvityPresenterImpl;
import com.baozi.mvp.base.BaseActivity;
import com.baozi.mvp.base.TempletActivity;
import com.baozi.mvp.helper.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/3/20.
 */

public class HomeActivity extends BaseActivity<HomeActvityPresenterImpl>
        implements HomeActvityContract.View {
    @NonNull
    @Override
    protected View initView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_index, null);
        return inflate;
    }

    @Override
    protected HomeActvityPresenterImpl initPresenter() {
        return new HomeActvityPresenterImpl();
    }

    @Override
    public ViewPager getContentViewPager() {
        return findView(R.id.vp_content);
    }

    @Override
    public List<Fragment> getFragments() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getTabs() {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("直播");
        tabs.add("推荐");
        tabs.add("追番");
        tabs.add("分区");
        tabs.add("发现");
        return tabs;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_index_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
