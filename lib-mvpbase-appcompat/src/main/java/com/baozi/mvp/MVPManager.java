package com.baozi.mvp;

import com.baozi.mvp.helper.ToolbarOptions;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class MVPManager {
    public static ToolbarOptions mToolbarOptions;

    public static ToolbarOptions getToolbarOptions() {
        if (mToolbarOptions == null) {
            return ToolbarOptions.Create();
        }
        return mToolbarOptions;
    }

    public static void setToolbarOptions(ToolbarOptions toolbarOptions) {
        mToolbarOptions = toolbarOptions;
    }

}
