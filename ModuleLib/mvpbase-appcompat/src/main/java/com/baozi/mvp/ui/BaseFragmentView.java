package com.baozi.mvp.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseFragmentView extends UIView {
    Bundle getBundle();
    Fragment getFragment();
}
