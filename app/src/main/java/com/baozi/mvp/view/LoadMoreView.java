package com.baozi.mvp.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by baozi on 2017/12/5.
 */

public interface LoadMoreView {
    Context getContext();
    @Nullable
    SwipeRefreshLayout getSwipeRefreshLayout();

    RecyclerView getRecyclerView();

}
