package com.baozi.mvpdemo.ui.view;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baozi.mvpdemo.helper.ToolbarHelper;

/**
 * Created by baozi on 2017/2/20.
 * 用户页面,操作页面，对应Activity,frgament...
 */

public interface UIView extends BaseView {
    /**
     * id查找控件
     * 省去findviewById的强转操作
     *
     * @param viewId 控件id
     * @param <V>    控件类型
     * @return V extends View
     */
    <V extends View> V findView(int viewId);

    /**
     * res资源获取
     *
     * @return
     */
    Resources getResources();

    /**
     * 回退
     */
    void onBack();


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

    /**
     * 获取Acitivity
     *
     * @return
     */
    Activity getActivity();

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment);

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment, String tag);

    /**
     * 获得getSupportActionBar
     *
     * @return
     */
    ActionBar getSupportActionBar();

    /**
     * 设置getSupportActionBar
     *
     * @param toolbar
     * @return
     */
    void setSupportActionBar(Toolbar toolbar);

    /**
     * 获得ToolbarHelper,Presenter可以通过ToolbarHelper的来控制toolbar
     */
    ToolbarHelper getToolbarHelper();
}
