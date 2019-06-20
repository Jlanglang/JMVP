package com.baozi.mvp.view;

import android.support.design.widget.TabLayout;

public interface TabLayoutView extends BaseView {

    TabLayout getTabLayout();

    String[] getTabString();

    int[] getTabDrawables();

    int getTabLayoutItem();
}
