package com.baozi.mvp.tempalet.helper.toolbar;

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
    public void setToolbarOptions(ToolbarOptions toolbarOptions) {

    }

}
