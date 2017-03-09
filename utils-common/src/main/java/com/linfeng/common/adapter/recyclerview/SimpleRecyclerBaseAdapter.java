package com.linfeng.common.adapter.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import com.linfeng.common.adapter.recyclerview.base.ItemViewDelegate;
import com.linfeng.common.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class SimpleRecyclerBaseAdapter<T> extends MultiItemTypeAdapter<T> {
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public SimpleRecyclerBaseAdapter(Context context, int layoutId, List<T> datas) {
        super(context, datas);
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return mLayoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                SimpleRecyclerBaseAdapter.this.convert(holder, t, position);
            }
        });
    }

    public SimpleRecyclerBaseAdapter(Context context, int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    protected abstract void convert(ViewHolder holder, T t, int position);

    public void replaceAll(List<T> rows) {
        if (rows == null) {
            return;
        } else {
            mDatas = rows;
            notifyDataSetChanged();
        }
    }

    public void replace(T data, int position) {
        if (data == null || position < 0) {
            return;
        } else {
            mDatas.set(position,data);
            notifyDataSetChanged();
        }
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void addAll(List<T> rows) {
        if (rows == null) {
            return;
        } else if (mDatas.size() == 0) {
            replaceAll(rows);
        } else {
            mDatas.addAll(rows);
            notifyDataSetChanged();
        }
    }

    public void addData(int position, T t) {
        mDatas.add(position, t);
        notifyItemInserted(position);
    }
}
