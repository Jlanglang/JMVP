package com.baozi.jrecyclerviewadapter.adapter.recyclerview;



import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class ItemManager<T> {

    private SimpleRecyclerAdapter<T> mAdapter;

    public ItemManager(SimpleRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SimpleRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
    }

    public abstract void addAllItems(List<T> items);

    public abstract void replaceAllItem(List<T> items);

    //刷新
    public void notifyDataChanged() {
        mAdapter.notifyDataSetChanged();
    }
}