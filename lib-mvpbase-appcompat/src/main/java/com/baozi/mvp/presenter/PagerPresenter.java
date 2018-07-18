package com.baozi.mvp.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozi.mvp.view.PagerView;


/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class PagerPresenter {

    private PagerView mView;
    private PagerAdapter mAdapter;

    public PagerPresenter(PagerView mView) {
        this.mView = mView;
    }

    public void onCreate() {
        initViewPager();
        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout tablayout = mView.getTablayout();
        if (tablayout == null || mView.getTabString() == null) {
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
        mView.getViewPager().setOffscreenPageLimit(mView.getTabString().length);
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

    private void initViewPager() {
        ViewPager viewPager = mView.getViewPager();
        viewPager.setAdapter(getAdapter());
    }

    protected PagerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new PagerAdapter() {
                // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
                @Override
                public Object instantiateItem(ViewGroup view, int position) {
                    view.addView(mView.getPager().get(position));
                    return mView.getPager().get(position);
                }

                @Override
                public int getItemPosition(Object object) {
                    return POSITION_NONE;
                }

                @Override
                public int getCount() {
                    return mView.getPager().size();
                }

                // 例如PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
                @Override
                public void destroyItem(ViewGroup view, int position, Object object) {
                    view.removeView(mView.getPager().get(position));
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            };
        }
        return mAdapter;
    }
}
