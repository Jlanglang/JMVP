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
import com.baozi.homemodle.event.TestEvent;
import com.baozi.mvp.helper.ToolbarHelper;
import com.linfeng.common.utils.ToastUtil;
import com.linfeng.rx_retrofit_network.location.rxandroid.SimpleSubscriber;
import com.linfeng.rx_retrofit_network.location.rxandroid.SimpleTransformer;
import com.linfeng.rx_retrofit_network.location.rxbus.RxBus;


/**
 * Created by Administrator on 2017/03/28
 */

public class HomeActivityPresenterImpl extends JBasePresenter<HomeActvityContract.View>
        implements HomeActvityContract.Presenter {

    @Override
    public void onCreate() {
        Toolbar toolbar = mView.findView(R.id.tl_costom);
        ToolbarHelper.SimpleInitToolbar(mView.getContext(), toolbar, false);

        TabLayout tabLayout = mView.findView(R.id.tab_layout);
        ViewPager viewPager = mView.getContentViewPager();

        viewPager.setAdapter(new HomePageAdapter(mView.getAppcompatActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        RxBus.getDefault().toObservable(TestEvent.class)
                .compose(new SimpleTransformer<TestEvent>())
                .subscribe(new SimpleSubscriber<TestEvent>() {
                    @Override
                    public void call(TestEvent testEvent) {
                        ToastUtil.showToast(mView.getContext(), testEvent.code + "");
                        mDisposable.dispose();
                    }
                });
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