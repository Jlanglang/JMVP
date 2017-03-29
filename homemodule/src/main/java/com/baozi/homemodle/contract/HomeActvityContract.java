package com.baozi.homemodle.contract;

import android.app.Fragment;
import android.support.v4.view.ViewPager;

import com.baozi.mvp.ui.BaseActivityView;
import com.baozi.mvp.ui.TempletView;

import java.util.List;

/**
 * Created by baozi on 2017/3/20.
 */

public class HomeActvityContract {

    public interface View extends BaseActivityView {
        //contentview为viewpage+fragment
        ViewPager getContentViewPager();

        List<Fragment> getFragments();

        List<String> getTabs();
    }

    public interface Presenter {
    }

    public interface Model {
    }


}