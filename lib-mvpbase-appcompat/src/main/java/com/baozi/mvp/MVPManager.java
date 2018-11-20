package com.baozi.mvp;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.StyleRes;

import com.baozi.mvp.templet.options.ContentOptions;
import com.baozi.mvp.templet.options.ToolbarOptions;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class MVPManager {
    private static ToolbarOptions mToolbarOptions;
    private static ContentOptions mTempletContentOptions;
    @AnimRes
    @AnimatorRes
    private static int enterAnim;
    @AnimRes
    @AnimatorRes
    private static int exitAnim;
    @AnimRes
    @AnimatorRes
    private static int enterPopAnim;
    @AnimRes
    @AnimatorRes
    private static int exitPopAnim;
    private static int transactionStyle;

    public static void setFragmentAnim(@AnimatorRes @AnimRes int enter,
                                       @AnimatorRes @AnimRes int exit, @AnimatorRes @AnimRes int popEnter,
                                       @AnimatorRes @AnimRes int popExit) {
        enterAnim = enter;
        exitAnim = exit;
        enterPopAnim = popEnter;
        exitPopAnim = popExit;
    }

    public static void setFragmentTransaction(@StyleRes int styleRes) {
        transactionStyle = styleRes;
    }

    public static int getEnterAnim() {
        return enterAnim;
    }

    public static int getExitAnim() {
        return exitAnim;
    }

    public static int getEnterPopAnim() {
        return enterPopAnim;
    }

    public static int getExitPopAnim() {
        return exitPopAnim;
    }

    public static int getTransactionStyle() {
        return transactionStyle;
    }


    public static ToolbarOptions getToolbarOptions() {
        if (mToolbarOptions == null) {
            return ToolbarOptions.Create();
        }
        return mToolbarOptions.clone();
    }

    public static void setToolbarOptions(ToolbarOptions toolbarOptions) {
        mToolbarOptions = toolbarOptions;
    }

    public static ContentOptions getContentOptions() {
        if (mTempletContentOptions == null) {
            mTempletContentOptions = ContentOptions.create();
        }
        return mTempletContentOptions.clone();
    }

    public static void setContentOptions(ContentOptions templetContentOptions) {
        mTempletContentOptions = templetContentOptions;
    }

}
