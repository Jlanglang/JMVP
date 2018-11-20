package com.baozi.mvp.presenter;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozi.mvp.view.PagerFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class PagerFragmentPresenter {
    private List<Fragment> fragments;
    private PagerFragmentView mView;

    private static Fragment getFragment(Class<? extends Fragment> zClass) {
        try {
            return zClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PagerFragmentPresenter(PagerFragmentView mView) {
        this.mView = mView;
    }

    public void onCreate() {
        initViewpager();
        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout tablayout = mView.getTablayout();
        if (tablayout == null) {
            return;
        }
        tablayout.setupWithViewPager(mView.getViewPager());
        //条目数
        int size = getFragments().size();

//        int tabLayoutItem = mView.getTabLayoutItem();
        String[] tabString = mView.getTabString();
        int[] tabImage = mView.getTabDrawables();
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            int image = 0;
            if (tabImage != null) {
                image = tabImage[i];
            }
            String tabStr = "";
            if (tabString != null) {
                tabStr = tabString[i];
            }
            bindTab(tab, image, tabStr);
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

    private void bindTab(TabLayout.Tab tab, int image, String tabStr) {
        int tabLayoutItem = mView.getTabLayoutItem();
        if (tabLayoutItem == 0 && tab != null) {
            tab.setText(tabStr);
            if (image == 0) {
                return;
            }
            tab.setIcon(image);
            return;
        }
        if (tabLayoutItem != 0) {
            ViewGroup inflate = (ViewGroup) LayoutInflater.from(mView.getContext()).inflate(tabLayoutItem, null);
            int childCount = inflate.getChildCount();
            for (int j = 0; j < childCount; j++) {
                View childAt = inflate.getChildAt(j);
                if (childAt instanceof ImageView) {
                    if (image != 0) {
                        childAt.setVisibility(View.VISIBLE);
                        ((ImageView) childAt).setImageResource(image);
                    } else {
                        childAt.setVisibility(View.GONE);
                    }
                }
                if (childAt instanceof TextView) {
                    childAt.setVisibility(View.VISIBLE);
                    ColorStateList tabTextColors = mView.getTablayout().getTabTextColors();
                    ((TextView) childAt).setText(tabStr);
                    ((TextView) childAt).setTextColor(tabTextColors);
                }
            }
            tab.setCustomView(inflate);
        }
    }

    private void initViewpager() {
        ViewPager viewPager = mView.getViewPager();
        viewPager.setAdapter(getAdapter());
    }

    public List<Fragment> getFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            Class<? extends Fragment>[] fragments = mView.getFragments();
            for (int i = 0; i < fragments.length; i++) {
                Fragment fragment = getFragment(fragments[i]);
                this.fragments.add(fragment);
            }
        }
        return fragments;
    }

    protected FragmentStatePagerAdapter getAdapter() {
        return new FragmentStatePagerAdapter(mView.getFgManager()) {
            @Override
            public Fragment getItem(int position) {
                if (getFragments().get(position) == null) {
                    Fragment fragment = getFragment(mView.getFragments()[position]);
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
        };
    }
}
