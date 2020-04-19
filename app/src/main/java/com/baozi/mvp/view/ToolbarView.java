package com.baozi.mvp.view;

import androidx.annotation.LayoutRes;
import android.view.View;

import com.baozi.mvp.tempalet.helper.toolbar.ToolbarHelper;
import com.baozi.mvp.tempalet.options.ToolbarOptions;

/**
 * @author jlanglang  2017/3/4 17:44
 * @版本 2.0
 * @Change
 */

public interface ToolbarView extends BaseView {
    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    ToolbarHelper getToolbarHelper();

    /**
     * 通过这个修改toolbar的样式layout,不需要可以传0或者-1;
     *
     * @return
     */
    @LayoutRes
    int getToolbarLayout();

    /**
     * 回退
     */
    void onBackPressed();

    View getContentView();

    ToolbarOptions getToolbarOptions();
}
