package com.baozi.mvpdemo.ui.view;

import com.baozi.mvpdemo.helper.ToolbarHelper;

/**
 * @author jlanglang  2017/3/4 16:49
 * @版本 2.0
 * @Change
 */
public interface ToolbarView extends BaseView{
    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    ToolbarHelper getToolbarHelper();
}
