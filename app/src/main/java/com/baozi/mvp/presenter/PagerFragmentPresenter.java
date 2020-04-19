package com.baozi.mvp.presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.baozi.mvp.view.PageFragmentView;
import com.baozi.mvp.view.TabLayoutView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class PagerFragmentPresenter extends PagerPresenter<Fragment> {
    private List<Fragment> fragments;

    public PagerFragmentPresenter(PageFragmentView pageView) {
        super(pageView);
    }

    public PagerFragmentPresenter(PageFragmentView pageView, TabLayoutView tablayoutView) {
        super(pageView, tablayoutView);
    }

    public List<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            if (pageView.getPage() != null) {
                fragments.addAll(pageView.getPage());
            }
        }
        return fragments;
    }

    public FragmentStatePagerAdapter getAdapter() {
        return new FragmentStatePagerAdapter(((PageFragmentView) pageView).getFgManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size();
            }
        };
    }
}
