package com.baozi.demo.ui.at;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.baozi.demo.R;
import com.baozi.demo.ui.fg.DemoFg;
import com.baozi.mvp.annotation.JView;
import com.baozi.mvp.presenter.EmptyPresenter;
import com.baozi.mvp.presenter.PagerFragmentPresenter;
import com.baozi.mvp.tempalet.TemplateActivity;
import com.baozi.mvp.view.PageFragmentView;
import com.baozi.mvp.view.TabLayoutView;

import java.util.Arrays;
import java.util.List;

@JView(layout = R.layout.at_viewpage)
public class PageAt extends TemplateActivity<EmptyPresenter>
        implements PageFragmentView, TabLayoutView {
    private List f = Arrays.asList(
            new DemoFg(),
            new DemoFg(),
            new DemoFg()
    );

    @Override
    protected void onPresentersCreate() {
        super.onPresentersCreate();
        PagerFragmentPresenter pagerFragmentPresenter =
                new PagerFragmentPresenter(this, this);
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
        return true;
    }

    @Override
    public TabLayout getTabLayout() {
        return findView(R.id.tb_content);
    }

    @Override
    public String[] getTabString() {
        return new String[]{"1", "2", "3"};
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
