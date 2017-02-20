package com.baozi.mvpdemo.ui.view;

import android.view.View;

/**
 * Created by baozi on 2017/2/20.
 * 用户页面,操作页面，对应Activity,frgament
 */

public interface BaseUIView extends BaseView {

    <V extends View> V findView(int viewId);

}
