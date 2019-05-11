package com.baozi.mvp.tempalet.helper;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvp.tempalet.options.ToolbarOptions;

/**
 *
 */
public class EmptyToolbarHelperImpl extends ToolbarHelper {

    @Override
    public boolean setScrollFlag(int viewId, int flag) {
        return false;
    }

    @Override
    public <V extends View> V findViewFromAppBar(int viewId) {
        return null;
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return null;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void setToolbarOptions(ToolbarOptions toolbarOptions) {

    }


    public void setTitle(@NonNull String str) {

    }

    @Override
    public void setTitle(@StringRes int str) {

    }


    @Override
    public void setCanBack(boolean canback) {

    }

    @Override
    public void setLeading(String leading) {

    }

    @Override
    public void setLeading(int leadRes) {

    }

    @Override
    public void addActions(View view) {

    }
}
