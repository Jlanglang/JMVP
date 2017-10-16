package com.baozi.mvp;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class MVPManager {
    public static int statusColor;
    public static int statusDrawable;
    public static int toolbarBackgroundColor;
    public static int toolbarBackgroundDrawable;

    public static void setStatusColor(@ColorRes int staus) {
        statusColor = staus;
    }

    public static void setStatusDrawable(@DrawableRes int statusDraw) {
        statusDrawable = statusDraw;
    }

    public static boolean isStatusBar() {
        return statusColor > 0 | statusDrawable > 0;
    }

    public static boolean isToolBar() {
        return toolbarBackgroundColor > 0 | toolbarBackgroundDrawable > 0;
    }

    public static void setToolbarBackgroundColor(int toolbarBackgroundColor) {
        MVPManager.toolbarBackgroundColor = toolbarBackgroundColor;
    }

    public static void setToolbarBackgroundDrawable(int toolbarBackgroundDrawable) {
        MVPManager.toolbarBackgroundDrawable = toolbarBackgroundDrawable;
    }
}
