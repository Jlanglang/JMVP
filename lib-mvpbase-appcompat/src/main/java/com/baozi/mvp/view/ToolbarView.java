package com.baozi.mvp.view;

import android.support.annotation.LayoutRes;

import com.baozi.mvp.templet.helper.ToolbarHelper;
import com.baozi.mvp.templet.options.ToolbarOptions;

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
     * 是否使用MaterialDesign风格
     *
     * @return
     */
    boolean isMaterialDesign();

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

    ToolbarOptions getToolbarOptions();
}
