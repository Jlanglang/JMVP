package com.linfeng.mvp.model

/**
 * Created by baozi on 2017/12/5.
 */

interface LoadDataModel<T> {
    fun loadData(pageNum: Int, pageSize: Int, isRefresh: Boolean): T
}
