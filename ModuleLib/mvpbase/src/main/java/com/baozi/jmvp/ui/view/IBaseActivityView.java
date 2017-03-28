package com.baozi.jmvp.ui.view;

import android.app.FragmentManager;
import android.content.Intent;

/**
 * Created by baozi on 2016/11/24.
 */
public interface IBaseActivityView extends IUIView {
    Intent getIntent();

    FragmentManager getFragmentManager();

    android.support.v4.app.FragmentManager getSupportFragmentManager();
}
