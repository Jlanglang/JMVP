package com.baozi.mvpdemo.ui.view;

import com.baozi.mvpdemo.helper.ToolbarHelper;

/**
 * @author jlanglang  2017/3/4 17:44
 * @版本 2.0
 * @Change
 */

public interface IToolbarView extends IUIView {
    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    ToolbarHelper getToolbarHelper();
//
//    /**
//     * 是否使用MaterialDesign风格
//     *
//     * @return
//     */
//    boolean isMaterialDesign();

    int initToolbarLayout();

//    /**
//     * MaterialDesign风格,普通风格之间转换
//     *
//     * @param isMaterialDesign
//     */
//    void setMaterialDesignEnabled(boolean isMaterialDesign);

//    ActionBar getSupportActionBar();

//    void setSupportActionBar(@Nullable Toolbar toolbar);
}
