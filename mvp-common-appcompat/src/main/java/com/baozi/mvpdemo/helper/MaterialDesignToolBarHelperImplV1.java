package com.baozi.mvpdemo.helper;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.mvpdemo.R;
import com.baozi.mvpdemo.ui.view.UIView;

/**
 * @author jlanglang  2017/3/1 16:44
 * @版本 2.0
 * @Change
 */

class MaterialDesignToolBarHelperImplV1 extends BaseToolBarHelperImpl {

    MaterialDesignToolBarHelperImplV1(@NonNull UIView uiView, View rootView, int toolbarLayout) {
        super(uiView, rootView, toolbarLayout);
    }

    @Override
    public void initToolbar() {
        AppBarLayout appBarLayout = (AppBarLayout) mRootView.findViewById(R.id.app_bar);
        appBarLayout.removeAllViews();
        View inflate = LayoutInflater.from(mUIView.getContext()).inflate(toolbarLayout, appBarLayout, true);
//        appBarLayout.removeAllViews();
//        appBarLayout.addView(inflate);
//        ViewStubCompat vs_toolbar = (ViewStubCompat) mRootView.findViewById(R.id.vs_toolbar);
//        if (vs_toolbar != null) {
//            vs_toolbar.setLayoutResource(toolbarLayout);
//            LinearLayout inflate = (LinearLayout) vs_toolbar.inflate();
//            CollapsingToolbarLayout viewById = (CollapsingToolbarLayout) inflate.findViewById(R.id.toolbar_layout);
//            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) viewById.getLayoutParams();
//            layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//            mToolbar = (Toolbar) inflate.findViewById(R.id.tl_costom);
//        } else {
        mToolbar = (Toolbar) inflate.findViewById(R.id.tl_costom);
//        }
        mUIView.setSupportActionBar(mToolbar);
    }

    @Override
    public void setMaterialDesignEnabled(boolean isMaterialDesign) {
        super.setMaterialDesignEnabled(isMaterialDesign);
        ActionBar supportActionBar = mUIView.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayUseLogoEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowHomeEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowCustomEnabled(isMaterialDesign);
            supportActionBar.setDisplayHomeAsUpEnabled(isMaterialDesign);
            supportActionBar.setDisplayShowTitleEnabled(isMaterialDesign);
        }
    }

}
