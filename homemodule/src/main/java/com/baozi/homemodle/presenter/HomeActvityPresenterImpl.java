package com.baozi.homemodle.presenter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.frame.JBasePresenter;
import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.HomeActvityContract;
import com.baozi.mvp.helper.ToolbarHelper;

/**
 * Created by Administrator on 2017/03/28
 */

public class HomeActvityPresenterImpl extends JBasePresenter<HomeActvityContract.View>
        implements HomeActvityContract.Presenter {

    @Override
    public void onCreate() {
        Toolbar toolbar = mView.findView(R.id.tl_costom);
        ToolbarHelper.SimpleInitToolbar(mView.getContext(), toolbar);
        ViewPager view = mView.getContentViewPager();
    }

    @Override
    public void loadData() {

    }
}