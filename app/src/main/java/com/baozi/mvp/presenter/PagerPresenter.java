package com.baozi.mvp.presenter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozi.mvp.view.PageView;
import com.baozi.mvp.view.TabLayoutView;


/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class PagerPresenter<T> {
    protected PageView<T> pageView;
    protected TabLayoutView tablayoutView;
    protected PagerAdapter mAdapter;

    public PagerPresenter(PageView<T> pageView) {
        this(pageView, null);
    }

    public PagerPresenter(PageView<T> pageView, TabLayoutView tablayoutView) {
        this.pageView = pageView;
        this.tablayoutView = tablayoutView;
    }

    public void onCreate() {
        initViewPager();
        initTabLayout();
    }


    private void bindTab(TabLayout.Tab tab, int image, String tabStr) {
        int tabLayoutItem = tablayoutView.getTabLayoutItem();
        if (tabLayoutItem == 0 && tab != null) {
            tab.setText(tabStr);
            if (image == 0) {
                return;
            }
            tab.setIcon(image);
            return;
        }
        if (tabLayoutItem != 0) {
            ViewGroup inflate = (ViewGroup) LayoutInflater.from(pageView.getContext()).inflate(tabLayoutItem, null);
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
                    ColorStateList tabTextColors = tablayoutView.getTabLayout().getTabTextColors();
                    ((TextView) childAt).setText(tabStr);
                    ((TextView) childAt).setTextColor(tabTextColors);
                }
            }
            tab.setCustomView(inflate);
        }
    }

    private void initTabLayout() {
        if (tablayoutView == null) {
            return;
        }
        TabLayout tablayout = tablayoutView.getTabLayout();
        tablayout.setupWithViewPager(pageView.getViewPage());
        //条目数
        int size = pageView.getPage().size();
        String[] tabString = tablayoutView.getTabString();
        int[] tabImage = tablayoutView.getTabDrawables();
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
        pageView.getViewPage().setOffscreenPageLimit(pageView.getPage().size());
        if (!pageView.isAnimation()) {
            tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
                    pageView.getViewPage().setCurrentItem(position, false);
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
        if (pageView == null) {
            return;
        }
        ViewPager viewPager = pageView.getViewPage();
        viewPager.setAdapter(getAdapter());
    }

    protected PagerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new PagerAdapter() {
                // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup view, int position) {
                    T page = pageView.getPage().get(position);
                    if (page instanceof View) {
                        view.addView((View) page);
                    }
                    return page;
                }

                @Override
                public int getItemPosition(@NonNull Object object) {
                    return POSITION_NONE;
                }

                @Override
                public int getCount() {
                    return pageView.getPage().size();
                }

                // 例如PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
                @Override
                public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
                    T v = pageView.getPage().get(position);
                    if (v instanceof View) {
                        view.removeView((View) v);
                    }
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }
            };
        }
        return mAdapter;
    }
}
