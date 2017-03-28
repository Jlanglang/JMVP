package com.baozi.mvp.ui;

import android.app.FragmentManager;
import android.content.Intent;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseActivityView extends UIView {
    Intent getIntent();

    FragmentManager getFragmentManager();

}
