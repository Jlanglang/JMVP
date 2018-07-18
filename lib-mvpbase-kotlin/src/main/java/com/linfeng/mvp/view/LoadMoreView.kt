package com.linfeng.mvp.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

/**
 * Created by baozi on 2017/12/5.
 */

interface LoadMoreView {
    val context: Context
    val swipeRefreshLayout: SwipeRefreshLayout?

    val recyclerView: RecyclerView

}
