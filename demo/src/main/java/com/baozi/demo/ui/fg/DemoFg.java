package com.baozi.demo.ui.fg;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.baozi.demo.R;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.base.BaseFragment;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.presenter.PagerFragmentPresenter;
import com.baozi.mvp.view.PageFragmentView;
import com.baozi.mvp.view.TabLayoutView;

import java.util.Arrays;
import java.util.List;

@JView(layout = R.layout.fg_demo)
public class DemoFg extends BaseFragment<EmptyPresenter> implements PageFragmentView, TabLayoutView {
    private List f = Arrays.asList(
            new DemoChildFg(),
            new DemoChildFg(),
            new DemoChildFg()
    );

    @Override
    protected void onPresentersCreate() {
        super.onPresentersCreate();
        PagerFragmentPresenter pagerFragmentPresenter =
                new PagerFragmentPresenter(this, this);
        pagerFragmentPresenter.onCreate();
    }

    @Override
    public boolean isLazy() {
        return true;
    }

    @Override
    public FragmentManager getFgManager() {
        return getChildFragmentManager();
    }

    @Override
    public ViewPager getViewPage() {
        return findView(R.id.vp_content);
    }

    @Override
    public List<Fragment> getPage() {
        return f;
    }

    @Override
    public boolean isAnimation() {
        return false;
    }

    @Override
    public TabLayout getTabLayout() {
        return findView(R.id.tv_content);
    }

    @Override
    public String[] getTabString() {
        return new String[]{
                "1",
                "2",
                "3",
        };
    }

    @Override
    public int[] getTabDrawables() {
        return null;
    }

    @Override
    public int getTabLayoutItem() {
        return 0;
    }
}
