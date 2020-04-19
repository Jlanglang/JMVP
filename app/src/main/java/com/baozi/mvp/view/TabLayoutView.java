package com.baozi.mvp.view;

import com.google.android.material.tabs.TabLayout;

public interface TabLayoutView extends BaseView {

    TabLayout getTabLayout();

    String[] getTabString();

    int[] getTabDrawables();

    int getTabLayoutItem();
}
