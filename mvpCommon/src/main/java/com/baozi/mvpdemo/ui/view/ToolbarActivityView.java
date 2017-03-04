package com.baozi.mvpdemo.ui.view;

import com.baozi.mvpdemo.helper.ToolbarHelper;

/**
 * @author jlanglang  2017/3/4 17:44
 * @版本 2.0
 * @Change
 */

public interface ToolbarActivityView extends UIView {
    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    ToolbarHelper getToolbarHelper();
}
