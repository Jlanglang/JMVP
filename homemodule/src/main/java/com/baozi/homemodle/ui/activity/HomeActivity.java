package com.baozi.homemodle.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.HomeActvityContract;
import com.baozi.homemodle.presenter.HomeActvityPresenterImpl;
import com.baozi.homemodle.ui.fragment.IndexLiveListFragment;
import com.baozi.mvp.base.BaseActivity;
import com.baozi.mvp.presenter.BasePresenter;
import com.baozi.treerecyclerview.factory.ItemConfig;
import com.linfeng.common.utils.AutoUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baozi on 2017/3/20.
 */

public class HomeActivity extends BaseActivity<HomeActvityPresenterImpl>
        implements HomeActvityContract.View {

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTabs;


    @NonNull
    @Override
    protected View initView(LayoutInflater inflater, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_activity_index, null);
        AutoUtils.setSize(this,true,720,1280);
        AutoUtils.auto(inflate);
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

    @NonNull
    @Override
    public List<Fragment> getFragments() {
        if (null == mFragments) {
            mFragments = new ArrayList<>();
            mFragments.add(new IndexLiveListFragment());
//            mFragments.add(new IndexLiveListFragment());
//            mFragments.add(new IndexLiveListFragment());
//            mFragments.add(new IndexLiveListFragment());
//            mFragments.add(new IndexLiveListFragment());
//            mFragments.add(new IndexLiveListFragment());
        }
        return mFragments;
    }

    @Override
    public List<String> getTabs() {
        if (null == mTabs) {
            mTabs = new ArrayList<>();

            mTabs.add("直播");
//            mTabs.add("推荐");
//            mTabs.add("追番");
//            mTabs.add("分区");
//            mTabs.add("动态");
//            mTabs.add("发现");
        }
        return mTabs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_index_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
