package com.baozi.homemodle.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.IndexLiveListFragmentContract;
import com.baozi.homemodle.presenter.IndexLiveListFragmentPresenterImpl;
import com.baozi.mvp.base.BaseFragment;

/**
 * @author jlanglang  2017/3/30 16:42
 * @版本 2.0
 * @Change
 */

public class IndexLiveListFragment extends BaseFragment<IndexLiveListFragmentPresenterImpl>
        implements IndexLiveListFragmentContract.View {

    @Override
    public View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_livelist, null);
    }


    @Override
    protected IndexLiveListFragmentPresenterImpl initPresenter() {
//        final int time = 100000;
//        //计时到time
//        Subscription subscribe = Observable.interval(1000, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .take(time)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//
//                    }
//                });
//        //倒计时
//        Subscription subscribe1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .take(time)
//                .map(new Func1<Long, Long>() {
//                    @Override
//                    public Long call(Long aLong) {
//                        return time - aLong;
//                    }
//                })
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//
//                    }
//                });


        return new IndexLiveListFragmentPresenterImpl();
    }
}
