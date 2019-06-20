package com.baozi.demo.ui.at;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.baozi.demo.R;
import com.baozi.demo.ui.fg.DemoFg;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.presenter.PagerFragmentPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.view.PageFragmentView;

import java.util.Arrays;
import java.util.List;

public class PageAt extends TemplateActivity<EmptyPresenter> implements PageFragmentView {
    private List f = Arrays.asList(
            new DemoFg(),
            new DemoFg(),
            new DemoFg(),
            new DemoFg(),
            new DemoFg()
    );

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.at_viewpage;
    }

    @NonNull
    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void onPresentersCreate() {
        super.onPresentersCreate();
        PagerFragmentPresenter pagerFragmentPresenter = new PagerFragmentPresenter(this);
        pagerFragmentPresenter.onCreate();
    }

    @Override
    public FragmentManager getFgManager() {
        return getSupportFragmentManager();
    }

    @Override
    public List<Fragment> getPage() {
        return f;
    }

    @Override
    public ViewPager getViewPage() {
        return findViewById(R.id.vp_content);
    }


    @Override
    public boolean isAnimation() {
        return false;
    }
}
