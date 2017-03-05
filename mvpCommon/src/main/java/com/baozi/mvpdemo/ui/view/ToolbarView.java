package com.baozi.mvpdemo.ui.view;

import com.baozi.mvpdemo.helper.ToolbarHelper;

/**
 * @author jlanglang  2017/3/4 17:44
 * @版本 2.0
 * @Change
 */

public interface ToolbarView extends UIView {
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
     * MaterialDesign风格,普通风格之间转换
     *
     * @param isMaterialDesign
     */
    void setMaterialDesignEnabled(boolean isMaterialDesign);
}
