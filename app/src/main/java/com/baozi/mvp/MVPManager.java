package com.baozi.mvp;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.StyleRes;

import com.baozi.mvp.tempalet.options.ContentOptions;
import com.baozi.mvp.tempalet.options.ToolbarOptions;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class MVPManager {
    private static ToolbarOptions mToolbarOptions;
    private static ContentOptions mContentOptions;
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

    /**
     * 防止影响全局,第二次获取只能获取clone的副本;
     *
     * @return
     */
    public static ContentOptions getContentOptions() {
        if (mContentOptions == null) {
            mContentOptions = ContentOptions.create();
        }
        return mContentOptions.clone();
    }

    public static void setContentOptions(ContentOptions templetContentOptions) {
        mContentOptions = templetContentOptions;
    }

    public static int getStatusDrawable() {
        return MVPManager.getToolbarOptions().getStatusDrawable();
    }
}
