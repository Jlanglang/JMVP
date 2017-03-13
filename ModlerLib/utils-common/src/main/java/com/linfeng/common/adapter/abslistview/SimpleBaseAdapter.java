package com.linfeng.common.adapter.abslistview;

import android.content.Context;

import java.util.List;

public abstract class SimpleBaseAdapter<T> extends MultiItemTypeAdapter<T>
{

    public SimpleBaseAdapter(Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {

            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
