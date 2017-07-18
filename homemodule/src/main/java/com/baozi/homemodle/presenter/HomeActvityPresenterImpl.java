package com.baozi.homemodle.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.baozi.frame.JBasePresenter;
import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.HomeActvityContract;
import com.baozi.mvp.helper.ToolbarHelper;


/**
 * Created by Administrator on 2017/03/28
 */

public class HomeActvityPresenterImpl extends JBasePresenter<HomeActvityContract.View>
        implements HomeActvityContract.Presenter {

    @Override
    public void onCreate() {
        Toolbar toolbar = mView.findView(R.id.tl_costom);
        ToolbarHelper.SimpleInitToolbar(mView.getContext(), toolbar, false);

        TabLayout tabLayout = mView.findView(R.id.tab_layout);
        ViewPager viewPager = mView.getContentViewPager();

        viewPager.setAdapter(new HomePageAdapter(mView.getAppcompatActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {
    }

    private class HomePageAdapter extends FragmentStatePagerAdapter {
        FragmentManager mFragmentManager;

        HomePageAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            return mView.getFragments().get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mView.getTabs().get(position);
        }

        @Override
        public int getCount() {
            return mView.getFragments().size();
        }

//        @Override
//        public Fragment instantiateItem(ViewGroup container, int position) {
//            Fragment fragment = (Fragment) super.instantiateItem(container, position);
//            mFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
//            return fragment;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
////            super.destroyItem(container, position, object);
//            mFragmentManager.beginTransaction().hide(getItem(position)).commitAllowingStateLoss();
//        }

    }
}