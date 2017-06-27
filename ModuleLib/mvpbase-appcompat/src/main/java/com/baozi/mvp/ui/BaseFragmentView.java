package com.baozi.mvp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by baozi on 2016/11/24.
 */
public interface BaseFragmentView extends UIView {
    Bundle getBundle();
    Fragment getFragment();
}
