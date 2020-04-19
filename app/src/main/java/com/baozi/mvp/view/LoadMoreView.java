package com.baozi.mvp.view;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by baozi on 2017/12/5.
 */

public interface LoadMoreView {
    Context getContext();
    @Nullable
    SwipeRefreshLayout getSwipeRefreshLayout();

    RecyclerView getRecyclerView();

}
