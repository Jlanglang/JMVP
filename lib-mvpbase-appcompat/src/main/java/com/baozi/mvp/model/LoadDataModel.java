package com.baozi.mvp.model;

/**
 * Created by baozi on 2017/12/5.
 */

public interface LoadDataModel<T> {
    T loadData(int pageNum, int pageSize, boolean isRefresh);
}
