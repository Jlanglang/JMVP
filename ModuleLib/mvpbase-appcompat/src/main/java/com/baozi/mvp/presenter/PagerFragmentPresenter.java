package com.baozi.mvp.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozi.mvp.factory.FragmentFactory;
import com.baozi.mvp.view.PagerFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class PagerFragmentPresenter extends PagerPresenter {
    private List<Fragment> fragments;
    private PagerFragmentView mView;

    public PagerFragmentPresenter(PagerFragmentView mView) {
        super(mView);
        this.mView = mView;
    }

    public void onCreate() {
        initViewpager();
        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout tablayout = mView.getTablayout();
        if (tablayout == null || mView.getTabDrawables() == null || mView.getTabString() == null) {
            return;
        }
        tablayout.setupWithViewPager(mView.getViewPager());

        int[] tabImage = mView.getTabDrawables();
        String[] tabString = mView.getTabString();
        int tabLayoutItem = mView.getTabLayoutItem();
        for (int i = 0; i < tabImage.length; i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null && tabLayoutItem != 0) {
                ViewGroup inflate = (ViewGroup) LayoutInflater.from(mView.getContext()).inflate(tabLayoutItem, null);
                int childCount = inflate.getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View childAt = inflate.getChildAt(j);
                    if (childAt instanceof ImageView) {
                        ((ImageView) childAt).setImageResource(tabImage[i]);
                    }
                    if (childAt instanceof TextView) {
                        ((TextView) childAt).setText(tabString[i]);
                    }
                }
                tab.setCustomView(inflate);
            } else {
                tab = tablayout.newTab();
                tab.setText(tabString[i]);
                tab.setIcon(tabImage[i]);
            }
        }
        mView.getViewPager().setOffscreenPageLimit(mView.getFragments().length);
        if (!mView.isAnimation()) {
            tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
                    mView.getViewPager().setCurrentItem(position, false);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    private void initViewpager() {
        ViewPager viewPager = mView.getViewPager();
        viewPager.setAdapter(getAdapter());
    }

    public List<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            Class<Fragment>[] fragments = mView.getFragments();
            for (int i = 0; i < fragments.length; i++) {
                Fragment fragment = FragmentFactory.getFragment(fragments[i]);
                this.fragments.add(fragment);
            }
        }
        return fragments;
    }

    @Override
    protected FragmentStatePagerAdapter getAdapter() {
        return new JFragmentPagerAdapter(mView.getFgManager());
    }

    private class JFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public JFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (getFragments().get(position) == null) {
                Fragment fragment = FragmentFactory.getFragment(mView.getFragments()[position]);
                getFragments().set(position, fragment);
            }
            return getFragments().get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mView.getTabString()[position];
        }

        @Override
        public int getCount() {
            return getFragments().size();
        }
    }
}
