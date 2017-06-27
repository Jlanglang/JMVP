package com.baozi.jrecyclerviewadapter.adapter.abslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    int layoutId;
    List<T> mDatas;

    public SimpleBaseAdapter(int layoutId, List<T> datas) {
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            viewHolder = new ViewHolder(context, itemView, parent, position);
            viewHolder.mLayoutId = layoutId;
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mPosition = position;
        }
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
