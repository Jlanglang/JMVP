package com.baozi.homemodle.presenter;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.frame.JBasePresenter;
import com.baozi.homemodle.R;
import com.baozi.homemodle.contract.IndexLiveListFragmentContract;
import com.linfeng.common.adapter.recyclerview.SimpleRecyclerBaseAdapter;
import com.linfeng.common.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/03/30
 */

public class IndexLiveListFragmentPresenterImpl extends JBasePresenter<IndexLiveListFragmentContract.View>
        implements IndexLiveListFragmentContract.Presenter {

    private SimpleRecyclerBaseAdapter mSimpleRecyclerBaseAdapter;

    @Override
    public void onCreate() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(i + "");
        }
        mSimpleRecyclerBaseAdapter = new SimpleRecyclerBaseAdapter<String>(mView.getContext(), R.layout.home_item_live_list, strings) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }
        };
        RecyclerView recyclerView = mView.findView(R.id.rl_content);
        recyclerView.requestDisallowInterceptTouchEvent(true);
        recyclerView.setAdapter(mSimpleRecyclerBaseAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = AutoUtils.getPercentHeightSize(20);
            }
        });
    }

    @Override
    public void loadData() {

    }
}